package br.edu.utfpr.pb.tcc.server.service.impl;

import br.edu.utfpr.pb.tcc.server.dto.AccountsPayableDto;
import br.edu.utfpr.pb.tcc.server.enumeration.CategoryType;
import br.edu.utfpr.pb.tcc.server.enumeration.InstallmentStatus;
import br.edu.utfpr.pb.tcc.server.model.AccountsPayable;
import br.edu.utfpr.pb.tcc.server.model.AccountsPayableInstallment;
import br.edu.utfpr.pb.tcc.server.model.Category;
import br.edu.utfpr.pb.tcc.server.model.User;
import br.edu.utfpr.pb.tcc.server.repository.AccountsPayableRepository;
import br.edu.utfpr.pb.tcc.server.service.IAccountsPayableInstallmentService;
import br.edu.utfpr.pb.tcc.server.service.IAccountsPayableService;
import br.edu.utfpr.pb.tcc.server.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AccountsPayableServiceImpl extends CrudServiceImpl<AccountsPayable, Long>
        implements IAccountsPayableService {

    private final AccountsPayableRepository repository;
    private final ModelMapper modelMapper;
    private final IAccountsPayableInstallmentService installmentService;
    private final ICategoryService categoryService;

    public AccountsPayableServiceImpl(AccountsPayableRepository repository,
                                      ModelMapper modelMapper,
                                      IAccountsPayableInstallmentService installmentService,
                                      ICategoryService categoryService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.installmentService = installmentService;
        this.categoryService = categoryService;
    }

    @Override
    protected JpaRepository<AccountsPayable, Long> getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public AccountsPayable save(AccountsPayable entity) {
        return super.save(entity);
    }

    @Override
    @Transactional
    public AccountsPayable save(AccountsPayableDto dto) {
        if (dto.getCategoryId() != null) {
            Category category = categoryService.findOne(dto.getCategoryId());
            if (category == null || category.getType() != CategoryType.SAIDA) {
                throw new IllegalArgumentException("A categoria deve ser do tipo 'SAÍDA'.");
            }
        }

        AccountsPayable accountsPayable = modelMapper.map(dto, AccountsPayable.class);

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        accountsPayable.setCreatedByUser(currentUser);
        accountsPayable.setCreatedAt(LocalDateTime.now());

        AccountsPayable savedAccountsPayable = super.save(accountsPayable);

        int numberOfInstallments = dto.getNumberOfInstallments();
        BigDecimal totalAmount = dto.getTotalAmount();
        BigDecimal installmentAmount = totalAmount.divide(BigDecimal.valueOf(numberOfInstallments), 2, RoundingMode.HALF_UP);
        LocalDate dueDate = dto.getDueDate();
        BigDecimal initialPayment = dto.getInitialPayment();

        for (int i = 1; i <= numberOfInstallments; i++) {
            AccountsPayableInstallment installment = AccountsPayableInstallment.builder()
                    .accountsPayable(savedAccountsPayable)
                    .installmentNumber(i)
                    .chargeMethod(savedAccountsPayable.getChargeMethod())
                    .category(savedAccountsPayable.getCategory())
                    .cashBox(savedAccountsPayable.getCashBox())
                    .amount(installmentAmount)
                    .dueDate(dueDate.plusMonths(i - 1))
                    .notes(savedAccountsPayable.getNotes())
                    .createdByUser(currentUser)
                    .createdAt(LocalDateTime.now())
                    .build();

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

        return savedAccountsPayable;
    }
}