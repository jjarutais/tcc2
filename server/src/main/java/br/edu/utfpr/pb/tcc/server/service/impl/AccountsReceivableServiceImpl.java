package br.edu.utfpr.pb.tcc.server.service.impl;

import br.edu.utfpr.pb.tcc.server.dto.AccountsReceivableDto;
import br.edu.utfpr.pb.tcc.server.enumeration.InstallmentStatus;
import br.edu.utfpr.pb.tcc.server.model.AccountsReceivable;
import br.edu.utfpr.pb.tcc.server.model.AccountsReceivableInstallment;
import br.edu.utfpr.pb.tcc.server.model.User;
import br.edu.utfpr.pb.tcc.server.repository.AccountsReceivableRepository;
import br.edu.utfpr.pb.tcc.server.service.IAccountsReceivableInstallmentService;
import br.edu.utfpr.pb.tcc.server.service.IAccountsReceivableService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AccountsReceivableServiceImpl extends CrudServiceImpl<AccountsReceivable, Long>
        implements IAccountsReceivableService {

    private final AccountsReceivableRepository repository;
    private final ModelMapper modelMapper;
    private final IAccountsReceivableInstallmentService installmentService;

    public AccountsReceivableServiceImpl(AccountsReceivableRepository repository,
                                         ModelMapper modelMapper,
                                         IAccountsReceivableInstallmentService installmentService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.installmentService = installmentService;
    }

    @Override
    protected JpaRepository<AccountsReceivable, Long> getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public AccountsReceivable save(AccountsReceivable entity) {
        return super.save(entity);
    }

    @Transactional
    public AccountsReceivable save(AccountsReceivableDto dto) {
        AccountsReceivable accountsReceivable = modelMapper.map(dto, AccountsReceivable.class);

        // Obtém o usuário logado
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        accountsReceivable.setCreatedByUser(currentUser);
        accountsReceivable.setCreatedAt(LocalDateTime.now());

        // Salva a entidade pai
        AccountsReceivable savedAccountsReceivable = super.save(accountsReceivable);

        // Lógica para criação das parcelas
        int numberOfInstallments = dto.getNumberOfInstallments();
        BigDecimal totalAmount = dto.getTotalAmount();
        BigDecimal installmentAmount = totalAmount.divide(BigDecimal.valueOf(numberOfInstallments), 2, RoundingMode.HALF_UP);
        LocalDate dueDate = dto.getDueDate();
        BigDecimal initialPayment = dto.getInitialPayment();

        for (int i = 1; i <= numberOfInstallments; i++) {
            AccountsReceivableInstallment installment = AccountsReceivableInstallment.builder()
                    .accountsReceivable(savedAccountsReceivable)
                    .installmentNumber(i)
                    .chargeMethod(savedAccountsReceivable.getChargeMethod())
                    .category(savedAccountsReceivable.getCategory())
                    .cashBox(savedAccountsReceivable.getCashBox())
                    .amount(installmentAmount)
                    .dueDate(dueDate.plusMonths(i - 1))
                    .notes(savedAccountsReceivable.getNotes())
                    .createdByUser(currentUser)
                    .createdAt(LocalDateTime.now())
                    .build();

            // Lógica para pagamento inicial
            if (initialPayment != null && initialPayment.compareTo(BigDecimal.ZERO) > 0 && i == 1) {
                installment.setAmountPaid(initialPayment);
                installment.setPaymentDate(LocalDate.now());
                installment.setStatus(InstallmentStatus.PAGO);
                installment.setUpdatedByUser(currentUser);
                installment.setUpdatedAt(LocalDateTime.now());
            } else {
                installment.setStatus(InstallmentStatus.PENDENTE);
            }

            installmentService.save(installment);
        }

        return savedAccountsReceivable;
    }
}