package br.edu.utfpr.pb.tcc.server.repository;

import br.edu.utfpr.pb.tcc.server.model.ClientSupplier;
import br.edu.utfpr.pb.tcc.server.enumeration.ClientSupplierType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientSupplierRepository extends JpaRepository<ClientSupplier, Long> {
    List<ClientSupplier> findByType(ClientSupplierType type);
    ClientSupplier findByCpf(String cpf);
    ClientSupplier findByCnpj(String cnpj);
}