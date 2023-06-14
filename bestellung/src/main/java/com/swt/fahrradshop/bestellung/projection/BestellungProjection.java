package com.swt.fahrradshop.bestellung.projection;

import org.springframework.beans.BeanUtils;
import com.swt.fahrradshop.bestellung.entity.BestellungEntity;
import com.swt.fahrradshop.bestellung.event.BestellungCreatedEvent;
import com.swt.fahrradshop.bestellung.repository.BestellungRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//CRUD operation into DB
public class BestellungProjection {

    @Autowired
    private  BestellungRepository bestellungRepository;


    @EventHandler
    public void on(BestellungCreatedEvent evt){

        //TODO -- Chaouite check if it already exists in DB

        BestellungEntity bestellung = new BestellungEntity(
                evt.getBestellungId(),
                evt.getBestellungsstatus(),
                evt.getKundenIdValueObject(),
                evt.getEinzelposten(),
                evt.getZahlungValueObject()
        );

        bestellungRepository.save(bestellung);
    }

}
