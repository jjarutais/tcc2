package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.ClientSupplierDto;
import br.edu.utfpr.pb.tcc.server.model.ClientSupplier;
import br.edu.utfpr.pb.tcc.server.service.IClientSupplierService;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.utfpr.pb.tcc.server.enumeration.ClientSupplierType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("client-suppliers")
public class ClientSupplierController extends CrudController<ClientSupplier, ClientSupplierDto, Long> {

    private final IClientSupplierService service;
    private final ModelMapper modelMapper;

    public ClientSupplierController(IClientSupplierService service, ModelMapper modelMapper) {
        super(ClientSupplier.class, ClientSupplierDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<ClientSupplier, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @GetMapping("/byType/{type}")
    public ResponseEntity<List<ClientSupplierDto>> findByType(@PathVariable ClientSupplierType type) {
        List<ClientSupplier> clientSuppliers = service.findByType(type);
        List<ClientSupplierDto> clientSupplierDtos = clientSuppliers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientSupplierDtos);
    }

}