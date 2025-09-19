package br.edu.utfpr.pb.tcc.server.service;

import br.edu.utfpr.pb.tcc.server.enumeration.ClientSupplierType;
import br.edu.utfpr.pb.tcc.server.model.ClientSupplier;
import java.util.List;

public interface IClientSupplierService extends ICrudService<ClientSupplier, Long> {
    List<ClientSupplier> findByType(ClientSupplierType type);
}