package com.swt.fahrradshop.logistik.projection;

import com.swt.fahrradshop.logistik.event.LogistikCreatedEvent;
import com.swt.fahrradshop.logistik.entity.Logistik;
import com.swt.fahrradshop.logistik.repository.LogistikRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
@Slf4j
@RequiredArgsConstructor
@Component
//This class will match the DB operations for every received event.
public class LogistikProjection {

    private final LogistikRepository logistikRepository;



    @EventHandler
    public void on(LogistikCreatedEvent logistikCreatedEvent) {
        log.debug("Handling a Logistik creation Command{}", logistikCreatedEvent.getLogistikId());
        Logistik logistik = new Logistik(
                logistikCreatedEvent.getLogistikId()

        );
        //Save the produkt in the DB normally we can only use save method but it is not working.
        this.logistikRepository.saveAndFlush(logistik);




    }

}
