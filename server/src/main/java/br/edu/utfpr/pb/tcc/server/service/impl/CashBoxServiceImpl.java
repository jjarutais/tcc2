package br.edu.utfpr.pb.tcc.server.service.impl;

import br.edu.utfpr.pb.tcc.server.model.CashBox;
import br.edu.utfpr.pb.tcc.server.repository.CashBoxRepository;
import br.edu.utfpr.pb.tcc.server.service.ICashBoxService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CashBoxServiceImpl extends CrudServiceImpl<CashBox, Long>
        implements ICashBoxService {

    private final CashBoxRepository cashBoxRepository;

    public CashBoxServiceImpl(CashBoxRepository cashBoxRepository) {
        this.cashBoxRepository = cashBoxRepository;
    }

    @Override
    protected JpaRepository<CashBox, Long> getRepository() {
        return cashBoxRepository;
    }
}