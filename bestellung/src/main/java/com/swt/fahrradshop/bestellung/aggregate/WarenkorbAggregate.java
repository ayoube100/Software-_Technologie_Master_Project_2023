package com.swt.fahrradshop.bestellung.aggregate;

import com.swt.fahrradshop.bestellung.command.CreateWarenkorbCommand;
import com.swt.fahrradshop.bestellung.event.WarenkorbCreatedEvent;
import com.swt.fahrradshop.bestellung.valueObject.Produkt;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.List;

@Aggregate
public class WarenkorbAggregate {

    @AggregateIdentifier
    private String warenkorbId;

    private String kundeId;
    private List<Produkt> produkteList;
    private BigDecimal gesamtpreis;

    //creats the CreateWarenkorbCommand bean
    public WarenkorbAggregate() {
    }

    //here we will calculate the gesamtpreis - logic always here
    @CommandHandler
    public WarenkorbAggregate(CreateWarenkorbCommand cmd) {

        WarenkorbCreatedEvent evt = WarenkorbCreatedEvent.builder()
                .warenkorbId(cmd.getWarenkorbId())
                .KundeId(cmd.getKundeId())
                .build();
        AggregateLifecycle.apply(evt);
    }

  /*  BigDecimal gesamtpreis = new BigDecimal(0);

        for (Produkt produkt: produkteList)
    {
        gesamtpreis = gesamtpreis.add(produkt.getProduktPreis().multiply(BigDecimal.valueOf(produkt.getProduktAnzahl())));
    }*/


    @EventSourcingHandler
    private void on(WarenkorbCreatedEvent evt){
        this.warenkorbId = evt.getWarenkorbId();
        this.kundeId= evt.getKundeId();
    }

}
