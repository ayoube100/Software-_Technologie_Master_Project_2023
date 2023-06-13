package com.swt.fahrradshop.katalog.projection;
import com.swt.fahrradshop.katalog.event.ProduktCreatedEvent;
import com.swt.fahrradshop.katalog.entity.Produkt;
import com.swt.fahrradshop.katalog.repository.ProduktRepository;

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
                produktCreatedEvent.getPrice()



        );
        //Save the produkt in the DB normally we can only use save method but it is not working.
        this.produktRepository.saveAndFlush(produkt);




    }

}
