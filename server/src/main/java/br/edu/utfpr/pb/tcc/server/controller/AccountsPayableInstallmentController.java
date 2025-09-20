package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.AccountsPayableInstallmentDto;
import br.edu.utfpr.pb.tcc.server.model.AccountsPayableInstallment;
import br.edu.utfpr.pb.tcc.server.service.IAccountsPayableInstallmentService;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts-payable-installments")
public class AccountsPayableInstallmentController extends CrudController<AccountsPayableInstallment, AccountsPayableInstallmentDto, Long> {

    private final IAccountsPayableInstallmentService service;
    private final ModelMapper modelMapper;

    public AccountsPayableInstallmentController(IAccountsPayableInstallmentService service, ModelMapper modelMapper) {
        super(AccountsPayableInstallment.class, AccountsPayableInstallmentDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<AccountsPayableInstallment, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}