package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.AccountsPayableDto;
import br.edu.utfpr.pb.tcc.server.model.AccountsPayable;
import br.edu.utfpr.pb.tcc.server.service.IAccountsPayableService;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("accounts-payable")
public class AccountsPayableController extends CrudController<AccountsPayable, AccountsPayableDto, Long> {

    private final IAccountsPayableService service;
    private final ModelMapper modelMapper;

    public AccountsPayableController(IAccountsPayableService service, ModelMapper modelMapper) {
        super(AccountsPayable.class, AccountsPayableDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<AccountsPayable, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @PostMapping
    public ResponseEntity<AccountsPayableDto> create(@RequestBody @Valid AccountsPayableDto entityDto) {
        AccountsPayable savedEntity = service.save(entityDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(convertToDto(savedEntity));
    }
}