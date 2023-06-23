package com.swt.fahrradshop.katalog.projection;
import com.swt.fahrradshop.katalog.event.ProduktCreatedEvent;
import com.swt.fahrradshop.katalog.entity.Produkt;
import com.swt.fahrradshop.katalog.event.ProduktDeletedEvent;
import com.swt.fahrradshop.katalog.event.ProduktUpdatedEvent;
import com.swt.fahrradshop.katalog.event.ProduktsReservedEvent;
import com.swt.fahrradshop.katalog.query.FindProduktQuery;
import com.swt.fahrradshop.katalog.repository.ProduktRepository;

import com.swt.fahrradshop.katalog.valueObject.ProduktDetails;
import com.swt.fahrradshop.katalog.valueObject.Verfuegbarkeit;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public void on(ProduktsReservedEvent event) {
        List<ProduktDetails> ProduktDetails= event.getProduktDetails();

        for (int i = 0; i < ProduktDetails.size(); i++) {
           ProduktDetails produktDetails =event.getProduktDetails().get(i);
           UUID produktId = produktDetails.getId();
           BigDecimal anzahl = produktDetails.getAnzahl();

            Optional<Produkt> optionalProduKt = Optional.ofNullable(produktRepository.findProduktById(produktId));
            optionalProduKt.ifPresent(produkt -> {
                // Update the projection based on the event data
                produkt.setVerfuegbarkeit(Verfuegbarkeit.NICHT_VERFUEGBAR); // Mark the product as nicht Verfügbar
                produkt.setAnzahl(produkt.getAnzahl().subtract(anzahl)); // Reduce the available quantity
                produktRepository.save(produkt);
            }
            );
        }
    }
    @EventHandler
    public void on(ProduktUpdatedEvent produktUpdatedEvent) {
     log.info("Handling a Produkt update Command{}", produktUpdatedEvent.getProduktId());


        Produkt produkt = new Produkt(
                produktUpdatedEvent.getProduktId(),
                produktUpdatedEvent.getNewName(),
                produktUpdatedEvent.getNewPreis(),
                produktUpdatedEvent.getNewAnzahl(),
                produktUpdatedEvent.getNewKategorie(),
                produktUpdatedEvent.getNewVerfuegbarkeit()



        );
//
      this.produktRepository.save(produkt);



    }
    @EventHandler
    public void on(ProduktDeletedEvent event) {
        log.debug("Handling DeleteProduktEvent for Produkt with ID: {}", event.getProduktId());
        produktRepository.deleteById(event.getProduktId());
    }
@QueryHandler
public Produkt handle(FindProduktQuery query) {
    log.debug("Handling FindProdukttQuery query: {}", query);
    return this.produktRepository.findById(query.getProduktId()).orElse(null);
}
}
