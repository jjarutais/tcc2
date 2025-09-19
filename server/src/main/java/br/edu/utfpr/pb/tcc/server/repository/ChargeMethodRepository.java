package br.edu.utfpr.pb.tcc.server.repository;

import br.edu.utfpr.pb.tcc.server.model.ChargeMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeMethodRepository extends JpaRepository<ChargeMethod, Long> {
}