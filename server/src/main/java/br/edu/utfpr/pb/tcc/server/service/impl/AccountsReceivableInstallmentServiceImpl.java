package br.edu.utfpr.pb.tcc.server.service.impl;

import br.edu.utfpr.pb.tcc.server.model.AccountsReceivableInstallment;
import br.edu.utfpr.pb.tcc.server.repository.AccountsReceivableInstallmentRepository;
import br.edu.utfpr.pb.tcc.server.service.IAccountsReceivableInstallmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountsReceivableInstallmentServiceImpl extends CrudServiceImpl<AccountsReceivableInstallment, Long>
        implements IAccountsReceivableInstallmentService {

    private final AccountsReceivableInstallmentRepository repository;

    public AccountsReceivableInstallmentServiceImpl(AccountsReceivableInstallmentRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<AccountsReceivableInstallment, Long> getRepository() {
        return repository;
    }
}