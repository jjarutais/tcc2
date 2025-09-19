package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.ChargeMethodDto;
import br.edu.utfpr.pb.tcc.server.model.ChargeMethod;
import br.edu.utfpr.pb.tcc.server.service.IChargeMethodService;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("charge-methods")
public class ChargeMethodController extends CrudController<ChargeMethod, ChargeMethodDto, Long> {

    private final IChargeMethodService service;
    private final ModelMapper modelMapper;

    public ChargeMethodController(IChargeMethodService service, ModelMapper modelMapper) {
        super(ChargeMethod.class, ChargeMethodDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<ChargeMethod, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }
}