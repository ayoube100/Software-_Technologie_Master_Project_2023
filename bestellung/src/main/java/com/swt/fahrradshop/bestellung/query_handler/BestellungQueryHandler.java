package com.swt.fahrradshop.bestellung.query_handler;

import com.swt.fahrradshop.bestellung.entity.BestellungEntity;
import com.swt.fahrradshop.bestellung.model.BestellungQueryModel;
import com.swt.fahrradshop.bestellung.query.FindBestellungByIdQuery;
import com.swt.fahrradshop.bestellung.query.FindBestellungenQuery;
import com.swt.fahrradshop.bestellung.repository.BestellungRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BestellungQueryHandler {

    @Autowired
    private BestellungRepository bestellungRepository;

    @QueryHandler
    public List<BestellungQueryModel> findBestellungen(FindBestellungenQuery qry) {
        List<BestellungQueryModel> bestellungen = new ArrayList<>();

        List<BestellungEntity> bestellungenInDB = bestellungRepository.findAll();

        if (bestellungenInDB.size() == 0) {
            log.info("Bestellungen DB is empty");
        } else {
            for (BestellungEntity bestellung : bestellungenInDB) {
                BestellungQueryModel bestellungQueryModel = new BestellungQueryModel();
                BeanUtils.copyProperties(
                        bestellung, bestellungQueryModel
                );
                bestellungen.add(bestellungQueryModel);
            }
        }
        return bestellungen;
    }

    @QueryHandler
    public BestellungQueryModel findBestellungById(FindBestellungByIdQuery qry) {
        BestellungEntity bestellungInDB = bestellungRepository.findByBestellungId(qry.getBestellungToBeFoundId());
        BestellungQueryModel bestellung = new BestellungQueryModel();
        BeanUtils.copyProperties(bestellungInDB, bestellung);
        return bestellung;
    }


}