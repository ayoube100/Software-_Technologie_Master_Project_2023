package com.swt.fahrradshop.katalog.aggregate;

import com.swt.fahrradshop.katalog.command.CreateProduktCommand;
import com.swt.fahrradshop.katalog.event.ProduktCreatedEvent;
import com.swt.fahrradshop.katalog.valueObject.KategorieEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
//this aggregate receives and handles the Commands and for every Command will dispatch a Query.
public class ProduktAggregate {
    @AggregateIdentifier
    private UUID id;
    private String Name;
    private BigDecimal Price;
    private KategorieEnum Kategorie;



    @CommandHandler
    public ProduktAggregate(CreateProduktCommand command){
        // this is an Axon annotation used to notify the ProduktAggregate that a new Produkt was creating by publishing a ProduktCreatedEvent
        AggregateLifecycle.apply(
                new ProduktCreatedEvent(
                        command.getProduktId(),
                        command.getName(),
                        command.getPrice()

                )
        );
    }
    @EventSourcingHandler
    public void on(ProduktCreatedEvent event){

        this.id = event.getProduktId();
        this.Name = event.getName();
        this.Price = event.getPrice();

    }


}
