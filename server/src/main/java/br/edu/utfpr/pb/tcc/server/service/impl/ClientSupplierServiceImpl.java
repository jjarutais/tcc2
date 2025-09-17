package br.edu.utfpr.pb.tcc.server.service.impl;

import br.edu.utfpr.pb.tcc.server.model.ClientSupplier;
import br.edu.utfpr.pb.tcc.server.repository.ClientSupplierRepository;
import br.edu.utfpr.pb.tcc.server.service.IClientSupplierService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import br.edu.utfpr.pb.tcc.server.enumeration.ClientSupplierType;
import java.util.List;

@Service
public class ClientSupplierServiceImpl extends CrudServiceImpl<ClientSupplier, Long>
        implements IClientSupplierService {

    private final ClientSupplierRepository repository;

    public ClientSupplierServiceImpl(ClientSupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<ClientSupplier, Long> getRepository() {
        return repository;
    }

    @Override
    public List<ClientSupplier> findByType(ClientSupplierType type) {
        return repository.findByType(type);
    }
}