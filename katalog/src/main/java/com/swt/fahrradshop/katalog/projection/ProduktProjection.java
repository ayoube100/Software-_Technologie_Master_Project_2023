package com.swt.fahrradshop.katalog.projection;
import com.swt.fahrradshop.katalog.event.ProduktCreatedEvent;
import com.swt.fahrradshop.katalog.entity.Produkt;
import com.swt.fahrradshop.katalog.event.ProduktDeletedEvent;
import com.swt.fahrradshop.katalog.event.ProduktUpdatedEvent;
import com.swt.fahrradshop.katalog.repository.ProduktRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
@Slf4j
@RequiredArgsConstructor
@Component
//This class will match the DB operations for every received event.
public class ProduktProjection {

    private final ProduktRepository produktRepository;



    @EventHandler
    public void on(ProduktCreatedEvent produktCreatedEvent) {
        log.debug("Handling a Produkt creation Command{}", produktCreatedEvent.getProduktId());
        Produkt produkt = new Produkt(
                produktCreatedEvent.getProduktId(),
                produktCreatedEvent.getName(),
                produktCreatedEvent.getPreis(),
                produktCreatedEvent.getAnzahl(),
                produktCreatedEvent.getKategorie(),
                produktCreatedEvent.getVerfuegbarkeit()




        );

        this.produktRepository.save(produkt);




    }
    @EventHandler
    public void on(ProduktUpdatedEvent produktUpdatedEvent) {
        log.debug("Handling a Produkt update Command{}", produktUpdatedEvent.getProduktId());
        Produkt produkt = new Produkt(
                produktUpdatedEvent.getProduktId(),
                produktUpdatedEvent.getNewName(),
                produktUpdatedEvent.getNewPreis(),
                produktUpdatedEvent.getNewAnzahl(),
                produktUpdatedEvent.getNewKategorie(),
                produktUpdatedEvent.getNewVerfuegbarkeit()



        );

        this.produktRepository.save(produkt);



    }
    @EventHandler
    public void on(ProduktDeletedEvent event) {
        log.debug("Handling DeleteProduktEvent for Produkt with ID: {}", event.getProduktId());
        produktRepository.deleteById(event.getProduktId());
    }
}
