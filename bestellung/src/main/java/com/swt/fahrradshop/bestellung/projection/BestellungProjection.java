package com.swt.fahrradshop.bestellung.projection;

import com.swt.fahrradshop.bestellung.entity.BestellungEntity;
import org.springframework.beans.BeanUtils;
import com.swt.fahrradshop.bestellung.event.BestellungCreatedEvent;
import com.swt.fahrradshop.bestellung.repository.BestellungRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
//CRUD operation into DB
public class BestellungProjection {


    private final BestellungRepository bestellungRepository;

    public BestellungProjection(BestellungRepository bestellungRepository) {
        this.bestellungRepository = bestellungRepository;
    }

    @EventHandler
    public void on(BestellungCreatedEvent evt) throws Exception{

        //TODO -- Chaouite check if it already exists in DB

        BestellungEntity bestellung = new BestellungEntity(
                evt.getBestellungId(),
                evt.getBestellungsstatus().toString(),
                evt.getKundenIdValueObject()
        );

        bestellungRepository.save(bestellung);
    }

}
