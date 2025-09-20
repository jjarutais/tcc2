package br.edu.utfpr.pb.tcc.server.service;

import br.edu.utfpr.pb.tcc.server.dto.AccountsPayableDto;
import br.edu.utfpr.pb.tcc.server.model.AccountsPayable;

public interface IAccountsPayableService extends ICrudService<AccountsPayable, Long> {
    AccountsPayable save(AccountsPayableDto dto);
}