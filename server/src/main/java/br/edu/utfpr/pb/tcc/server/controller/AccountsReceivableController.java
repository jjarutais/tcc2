package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.AccountsReceivableDto;
import br.edu.utfpr.pb.tcc.server.model.AccountsReceivable;
import br.edu.utfpr.pb.tcc.server.service.IAccountsReceivableService;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @PostMapping
    public ResponseEntity<AccountsReceivableDto> create(@RequestBody @Valid AccountsReceivableDto entityDto) {
        AccountsReceivable savedEntity = service.save(entityDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(convertToDto(savedEntity));
    }
}