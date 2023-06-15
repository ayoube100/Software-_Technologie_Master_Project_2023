package com.swt.fahrradshop.logistik.aggregate;

import com.swt.fahrradshop.logistik.command.CreateLogistikCommand;
import com.swt.fahrradshop.logistik.event.LogistikCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
//this aggregate receives and handles the Commands and for every Command will dispatch a Query.
public class LogistikAggregate {
    @AggregateIdentifier
    private UUID id;


    @CommandHandler
    public LogistikAggregate(CreateLogistikCommand command){
        // this is an Axon annotation used to notify the ProduktAggregate that a new Produkt was creating by publishing a ProduktCreatedEvent
        AggregateLifecycle.apply(
                new LogistikCreatedEvent(
                        command.getProduktId()
                )
        );
    }
    @EventSourcingHandler
    public void on(LogistikCreatedEvent event){

        this.id = event.getLogistikId();
    }


}
