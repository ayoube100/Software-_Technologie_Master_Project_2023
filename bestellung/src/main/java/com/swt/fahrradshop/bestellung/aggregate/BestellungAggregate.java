package com.swt.fahrradshop.bestellung.aggregate;

import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.event.BestellungCreatedEvent;
import com.swt.fahrradshop.bestellung.valueObject.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
public class BestellungAggregate {

    @AggregateIdentifier
    private String bestellungId;
    private BestellungsstatusEnum bestellungsstatusEnum;
    private KundeIdValueObject kundeIdValueObject;

    private String warenkorbId;
    private BigDecimal gesamtpreis;
    public BestellungAggregate() {
    }

    //triggered when commandGateway saved command
    @CommandHandler
    public BestellungAggregate(CreateBestellungCommand cmd) {
            //TODO -- Chaouite: validate create Bestellung Command

        BestellungCreatedEvent evt = BestellungCreatedEvent.builder()
                .bestellungId(cmd.getBestellungId())
                .bestellungsstatus(cmd.getBestellungsstatusEnum())
                .kundeIdValueObject(cmd.getKundeIdValueObject())
                .warenkorbId(cmd.getWarenkorbId())
                .gesamtpreis(cmd.getGesamtpreis())
                .build();

        //apply the event in Event source
        AggregateLifecycle.apply(evt);
    }

    //triggered when event dispatched => actualize the state of the aggregate
    @EventSourcingHandler
    public void on(BestellungCreatedEvent evt) throws Exception{
        this.bestellungId= evt.getBestellungId();
        this.bestellungsstatusEnum = evt.getBestellungsstatus();
        this.kundeIdValueObject = evt.getKundeIdValueObject();
        this.warenkorbId = evt.getWarenkorbId();
        this.gesamtpreis= evt.getGesamtpreis();
    }
}
