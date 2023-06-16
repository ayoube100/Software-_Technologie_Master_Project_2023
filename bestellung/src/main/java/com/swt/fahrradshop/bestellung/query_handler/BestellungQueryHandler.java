package com.swt.fahrradshop.bestellung.query_handler;

import com.swt.fahrradshop.bestellung.entity.BestellungEntity;
import com.swt.fahrradshop.bestellung.model.BestellungQueryModel;
import com.swt.fahrradshop.bestellung.query.FindBestellungByIdQuery;
import com.swt.fahrradshop.bestellung.query.FindBestellungenQuery;
import com.swt.fahrradshop.bestellung.repository.BestellungRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BestellungQueryHandler {
    @Autowired
    private BestellungRepository bestellungRepository;

    @QueryHandler
    public List<BestellungQueryModel> findBestellungen(FindBestellungenQuery qry){
        List<BestellungQueryModel> bestellungen = new ArrayList<>();

        List<BestellungEntity> bestellungenInDB = bestellungRepository.findAll();

        //TODO-- chaouite: check if list of Bestellungen is empty
        for (BestellungEntity bestellung : bestellungenInDB){
            BestellungQueryModel bestellungQueryModel = new BestellungQueryModel();
            BeanUtils.copyProperties(
                    bestellung,bestellungQueryModel
            );
            bestellungen.add(bestellungQueryModel);
        }
        return bestellungen;
    }

    @QueryHandler
    public BestellungQueryModel findBestellungById(FindBestellungByIdQuery qry){
        Optional<BestellungEntity> optionalBestellungEntity = bestellungRepository.findById(qry.getBestellungToBeFoundId());
        BestellungEntity bestellungInDB = optionalBestellungEntity.get();
        BestellungQueryModel bestellung = new BestellungQueryModel();
        BeanUtils.copyProperties(bestellungInDB,bestellung);
        return bestellung;
    }


}
