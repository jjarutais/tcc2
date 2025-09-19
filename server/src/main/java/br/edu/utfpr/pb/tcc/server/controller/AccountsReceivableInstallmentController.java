package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.AccountsReceivableInstallmentDto;
import br.edu.utfpr.pb.tcc.server.model.AccountsReceivableInstallment;
import br.edu.utfpr.pb.tcc.server.service.IAccountsReceivableInstallmentService;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts-receivable-installments")
public class AccountsReceivableInstallmentController extends CrudController<AccountsReceivableInstallment, AccountsReceivableInstallmentDto, Long> {

    private final IAccountsReceivableInstallmentService service;
    private final ModelMapper modelMapper;

    public AccountsReceivableInstallmentController(IAccountsReceivableInstallmentService service, ModelMapper modelMapper) {
        super(AccountsReceivableInstallment.class, AccountsReceivableInstallmentDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<AccountsReceivableInstallment, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}