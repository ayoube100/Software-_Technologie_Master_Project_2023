package com.swt.fahrradshop.bestellung.projection;

import com.swt.fahrradshop.bestellung.entity.WarenkorbEntity;
import com.swt.fahrradshop.bestellung.event.WarenkorbCreatedEvent;
import com.swt.fahrradshop.bestellung.repository.WarenkorbRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WarenkorbProjection {

    private WarenkorbRepository warenkorbRepository;

    public WarenkorbProjection(WarenkorbRepository warenkorbRepository) {
        this.warenkorbRepository = warenkorbRepository;
    }

    @EventHandler
    public void on(WarenkorbCreatedEvent evt){
        WarenkorbEntity warenkorb = new WarenkorbEntity(
                evt.getWarenkorbId(),
                evt.getKundeId(),
                null,
                null
        );

        warenkorbRepository.save(warenkorb);
    }
}
