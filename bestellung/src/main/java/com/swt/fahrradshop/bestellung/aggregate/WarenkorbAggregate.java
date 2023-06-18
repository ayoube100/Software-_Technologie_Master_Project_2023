package com.swt.fahrradshop.bestellung.aggregate;

import com.swt.fahrradshop.bestellung.command.AddProduktToWarenkorbCommand;
import com.swt.fahrradshop.bestellung.command.CreateWarenkorbCommand;
import com.swt.fahrradshop.bestellung.command.DeleteProduktFromWarenkorbCommand;
import com.swt.fahrradshop.bestellung.command.OrderWarenkorbCommand;
import com.swt.fahrradshop.bestellung.event.ProduktFromWarenkorbDeletedEvent;
import com.swt.fahrradshop.bestellung.event.ProduktToWarenkorbAddedEvent;
import com.swt.fahrradshop.bestellung.event.WarenkorbCreatedEvent;
import com.swt.fahrradshop.bestellung.event.WarenkorbOrderedEvent;
import com.swt.fahrradshop.bestellung.repository.WarenkorbRepository;
import com.swt.fahrradshop.bestellung.valueObject.WarenkorbProdukt;
import com.swt.fahrradshop.bestellung.valueObject.WarenkorbStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@Aggregate
public class WarenkorbAggregate {

    @AggregateIdentifier
    private String warenkorbId;

    private String kundeId;
    private List<WarenkorbProdukt> produkteList;
    private WarenkorbStatusEnum warenkorbStatus;
    public WarenkorbAggregate() {
    }

    @CommandHandler
    public WarenkorbAggregate(CreateWarenkorbCommand cmd) {
        WarenkorbCreatedEvent evt = WarenkorbCreatedEvent.builder()
                .warenkorbId(cmd.getWarenkorbId())
                .KundeId(cmd.getKundeId())
                .produkte(cmd.getProdukte())
                .warenkorbStatus(cmd.getWarenkorbStatus())
                .build();
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void handle(AddProduktToWarenkorbCommand cmd){
        // Existence check is in the projection
        ProduktToWarenkorbAddedEvent evt = new ProduktToWarenkorbAddedEvent(cmd.getWarenkorbId(), cmd.getProduktId(), cmd.getAnzahl());
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void handle(DeleteProduktFromWarenkorbCommand cmd){
        ProduktFromWarenkorbDeletedEvent evt = new ProduktFromWarenkorbDeletedEvent(cmd.getWarenkorbId(), cmd.getProduktId());
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void handle(OrderWarenkorbCommand cmd){
        WarenkorbOrderedEvent evt = new WarenkorbOrderedEvent(cmd.getWarenkorbId());
        AggregateLifecycle.apply(evt);
    }

    @EventSourcingHandler
    private void on(WarenkorbCreatedEvent evt){
        this.warenkorbId = evt.getWarenkorbId();
        this.kundeId= evt.getKundeId();
        this.produkteList = evt.getProdukte();
        this.warenkorbStatus= evt.getWarenkorbStatus();
    }

    @EventSourcingHandler
    private void on(ProduktToWarenkorbAddedEvent evt){
        this.warenkorbId = evt.getWarenkorbId();
    }

    @EventSourcingHandler
    private void on(ProduktFromWarenkorbDeletedEvent evt){
        this.warenkorbId = evt.getWarenkorbId();
    }

    @EventSourcingHandler
    private void on(WarenkorbOrderedEvent evt){
        this.warenkorbId = evt.getWarenkorbId();
    }

}
