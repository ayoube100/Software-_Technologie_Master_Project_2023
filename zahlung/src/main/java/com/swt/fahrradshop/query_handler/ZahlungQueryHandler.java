package com.swt.fahrradshop.query_handler;

import com.swt.fahrradshop.entity.ZahlungEntity;
import com.swt.fahrradshop.query.FindZahlungStatusByIdQuery;
import com.swt.fahrradshop.repository.ZahlungRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class ZahlungQueryHandler {

    private ZahlungRepository zahlungRepository;

    public ZahlungQueryHandler(ZahlungRepository zahlungRepository) {
        this.zahlungRepository = zahlungRepository;
    }

    @QueryHandler
    public String findZahlungStatusById(FindZahlungStatusByIdQuery qry) {
        ZahlungEntity zahlung = zahlungRepository.findZahlungEntitiesByZahlungId(qry.getZahlungId());
        return "Status of zahlung: " + zahlung.getZahlungId() + " is: " + zahlung.getZahlungsstatus();
    }
}
