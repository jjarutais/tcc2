package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.AccountsReceivableDto;
import br.edu.utfpr.pb.tcc.server.model.AccountsReceivable;
import br.edu.utfpr.pb.tcc.server.service.IAccountsReceivableService;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts-receivable")
public class AccountsReceivableController extends CrudController<AccountsReceivable, AccountsReceivableDto, Long> {

    private final IAccountsReceivableService service;
    private final ModelMapper modelMapper;

    public AccountsReceivableController(IAccountsReceivableService service, ModelMapper modelMapper) {
        super(AccountsReceivable.class, AccountsReceivableDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<AccountsReceivable, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}