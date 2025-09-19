package br.edu.utfpr.pb.tcc.server.service.impl;

import br.edu.utfpr.pb.tcc.server.model.AccountsReceivable;
import br.edu.utfpr.pb.tcc.server.repository.AccountsReceivableRepository;
import br.edu.utfpr.pb.tcc.server.service.IAccountsReceivableService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountsReceivableServiceImpl extends CrudServiceImpl<AccountsReceivable, Long>
        implements IAccountsReceivableService {

    private final AccountsReceivableRepository repository;

    public AccountsReceivableServiceImpl(AccountsReceivableRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<AccountsReceivable, Long> getRepository() {
        return repository;
    }
}