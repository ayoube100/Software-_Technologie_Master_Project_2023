package com.swt.fahrradshop.katalog.aggregate;

import com.swt.fahrradshop.katalog.command.CreateProduktCommand;
import com.swt.fahrradshop.katalog.command.DeleteProduktCommand;
import com.swt.fahrradshop.katalog.command.UpdateProduktCommand;
import com.swt.fahrradshop.katalog.event.ProduktCreatedEvent;
import com.swt.fahrradshop.katalog.event.ProduktDeletedEvent;
import com.swt.fahrradshop.katalog.event.ProduktUpdatedEvent;
import com.swt.fahrradshop.katalog.valueObject.Verfuegbarkeit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import com.swt.fahrradshop.katalog.valueObject.Kategorie;

import java.math.BigDecimal;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
//this aggregate receives and handles the Commands and for every Command will dispatch a Query.
public class ProduktAggregate {
    @AggregateIdentifier
    private UUID id;
    private String Name;
    private BigDecimal Preis;
    private BigDecimal Anzahl;
    private Kategorie Kategorie;
    private Verfuegbarkeit Verfuegbarkeit;



    @CommandHandler
    public ProduktAggregate(CreateProduktCommand command){
        // this is an Axon annotation used to notify the ProduktAggregate that a new Produkt was creating by publishing a ProduktCreatedEvent
        apply(
                new ProduktCreatedEvent(
                        command.getProduktId(),
                        command.getName(),
                        command.getPreis(),
                        command.getAnzahl(),
                        command.getKategorie(),
                        command.getVerfuegbarkeit()

                )
        );

    }
    @CommandHandler
    public ProduktAggregate(UpdateProduktCommand command){

                      //this is an Axon annotation used to apply the event to the ProduktAggregate
                      apply(new UpdateProduktCommand(command.getProduktId(),
                              command.getNewName(),
                              command.getNewPreis(),
                              command.getNewAnzahl(),
                              command.getNewKategorie(),
                              command.getNewVerfuegbarkeit()));

    }

    @CommandHandler
    public void handle(DeleteProduktCommand command, AggregateLifecycle lifecycle) {
        lifecycle.apply(new ProduktDeletedEvent(command.getProduktId()));
    }

    @EventSourcingHandler
    public void on(ProduktCreatedEvent event){

        this.id = event.getProduktId();
        this.Name = event.getName();
        this.Preis = event.getPreis();
        this.Kategorie=event.getKategorie();
        this.Verfuegbarkeit=event.getVerfuegbarkeit();

    }
    @EventSourcingHandler
    public void on(ProduktUpdatedEvent event){

        this.id = event.getProduktId();
        this.Name = event.getNewName();
        this.Preis= event.getNewPreis();
        this.Kategorie=event.getNewKategorie();
        this.Verfuegbarkeit=event.getNewVerfuegbarkeit();
    }
    @EventSourcingHandler
    public void on(ProduktDeletedEvent event) {
       markDeleted();
    }






    }