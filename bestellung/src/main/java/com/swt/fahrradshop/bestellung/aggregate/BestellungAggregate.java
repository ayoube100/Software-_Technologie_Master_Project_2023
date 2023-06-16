package com.swt.fahrradshop.bestellung.aggregate;

import com.swt.fahrradshop.bestellung.command.CancelBestellungCommand;
import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.event.BestellungCanceledEvent;
import com.swt.fahrradshop.bestellung.event.BestellungCreatedEvent;
import com.swt.fahrradshop.bestellung.valueObject.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
@Slf4j
public class BestellungAggregate {

    @AggregateIdentifier
    private String bestellungId;
    private BestellungsstatusEnum bestellungsstatusEnum;
    private String kundeId;

    private String warenkorbId;
    private BigDecimal gesamtpreis;

    public BestellungAggregate() {
    }

    /**
     * create a new Bestellung
     **/
    //triggered when commandGateway saved command
    @CommandHandler
    public BestellungAggregate(CreateBestellungCommand cmd) {
        //TODO -- Chaouite: validate create Bestellung Command
        apply( new BestellungCreatedEvent(cmd.getBestellungId(),
                cmd.getBestellungsstatus(),
                cmd.getKundeId(),
                cmd.getWarenkorbId(),
                cmd.getGesamtpreis()));
    }

    /**
     * cancel a Bestellung
     **/
    @CommandHandler
    public void handle(CancelBestellungCommand cmd) {
        apply(new BestellungCanceledEvent(cmd.getBestellungId()));
    }

    //triggered when event dispatched => actualize the state of the aggregate
    @EventSourcingHandler
    public void on(BestellungCreatedEvent evt) throws Exception {
        this.bestellungId = evt.getBestellungId();
        this.bestellungsstatusEnum = evt.getBestellungsstatus();
        this.kundeId = evt.getKundeId();
        this.warenkorbId = evt.getWarenkorbId();
        this.gesamtpreis = evt.getGesamtpreis();
    }

    @EventSourcingHandler
    public void on(BestellungCanceledEvent evt) {
        markDeleted();
    }
}
