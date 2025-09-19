package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.CashBoxDto;
import br.edu.utfpr.pb.tcc.server.model.CashBox;
import br.edu.utfpr.pb.tcc.server.service.ICashBoxService;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cash-boxes")
public class CashBoxController extends CrudController<CashBox, CashBoxDto, Long> {

    private final ICashBoxService service;
    private final ModelMapper modelMapper;

    public CashBoxController(ICashBoxService service, ModelMapper modelMapper) {
        super(CashBox.class, CashBoxDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<CashBox, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}