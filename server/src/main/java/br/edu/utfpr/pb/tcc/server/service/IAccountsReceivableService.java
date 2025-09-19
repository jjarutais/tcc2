package br.edu.utfpr.pb.tcc.server.service;

import br.edu.utfpr.pb.tcc.server.dto.AccountsReceivableDto;
import br.edu.utfpr.pb.tcc.server.model.AccountsReceivable;

public interface IAccountsReceivableService extends ICrudService<AccountsReceivable, Long> {
    AccountsReceivable save(AccountsReceivableDto dto);
}