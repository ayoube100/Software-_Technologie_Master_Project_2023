package com.swt.fahrradshop.projection;

import com.swt.fahrradshop.entity.ZahlungEntity;
import com.swt.fahrradshop.event.ZahlungProcessedEvent;
import com.swt.fahrradshop.repository.ZahlungRepository;
import org.axonframework.eventhandling.EventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Repository;

@Configuration
public class ZahlungProjection {

    private  ZahlungRepository zahlungRepository;

    @Autowired
    public ZahlungProjection(ZahlungRepository zahlungRepository) {
        this.zahlungRepository = zahlungRepository;
    }

    @EventHandler
    public void on (ZahlungProcessedEvent evt){
            zahlungRepository.save(new ZahlungEntity(
                    evt.getZahlungId(),
                    evt.getBestellungId(),
                    evt.getGesamtpreis(),
                    evt.getKreditKarte(),
                    evt.getZahlungsstatusEnum().toString()
                    )
            );
    }
}
