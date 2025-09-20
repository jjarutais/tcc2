package br.edu.utfpr.pb.tcc.server.service.impl;

import br.edu.utfpr.pb.tcc.server.model.AccountsPayableInstallment;
import br.edu.utfpr.pb.tcc.server.repository.AccountsPayableInstallmentRepository;
import br.edu.utfpr.pb.tcc.server.service.IAccountsPayableInstallmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountsPayableInstallmentServiceImpl extends CrudServiceImpl<AccountsPayableInstallment, Long>
        implements IAccountsPayableInstallmentService {

    private final AccountsPayableInstallmentRepository repository;

    public AccountsPayableInstallmentServiceImpl(AccountsPayableInstallmentRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<AccountsPayableInstallment, Long> getRepository() {
        return repository;
    }
}