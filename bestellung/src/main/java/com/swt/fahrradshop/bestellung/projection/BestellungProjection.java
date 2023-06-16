package com.swt.fahrradshop.bestellung.projection;

import com.swt.fahrradshop.bestellung.entity.BestellungEntity;
import com.swt.fahrradshop.bestellung.event.BestellungCanceledEvent;
import com.swt.fahrradshop.bestellung.event.BestellungCreatedEvent;
import com.swt.fahrradshop.bestellung.repository.BestellungRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
//CRUD operation into DB
public class BestellungProjection {


    private final BestellungRepository bestellungRepository;

    public BestellungProjection(BestellungRepository bestellungRepository) {
        this.bestellungRepository = bestellungRepository;
    }

    //triggered when Event is dispatched
    @EventHandler
    public void on(BestellungCreatedEvent evt) throws Exception{

        //TODO -- Chaouite check if it already exists in DB

        BestellungEntity bestellung = new BestellungEntity(
                evt.getBestellungId(),
                evt.getBestellungsstatus().toString(),
                evt.getKundeId(),
                evt.getWarenkorbId(),
                evt.getGesamtpreis()
        );

        //save the created entity in DB
        bestellungRepository.save(bestellung);
    }

    @EventHandler
    public void on(BestellungCanceledEvent evt) throws Exception {
        bestellungRepository.deleteById(evt.getBestellungId());
    }

}
