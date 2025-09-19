package br.edu.utfpr.pb.tcc.server.service.impl;

import br.edu.utfpr.pb.tcc.server.model.ChargeMethod;
import br.edu.utfpr.pb.tcc.server.repository.ChargeMethodRepository;
import br.edu.utfpr.pb.tcc.server.service.IChargeMethodService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ChargeMethodServiceImpl extends CrudServiceImpl<ChargeMethod, Long>
        implements IChargeMethodService {

    private final ChargeMethodRepository repository;

    public ChargeMethodServiceImpl(ChargeMethodRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<ChargeMethod, Long> getRepository() {
        return repository;
    }
}