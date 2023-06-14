package com.swt.fahrradshop.bestellung.aggregate;

import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.event.BestellungCreatedEvent;
import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import com.swt.fahrradshop.bestellung.valueObject.EinzelpostenValueObject;
import com.swt.fahrradshop.bestellung.valueObject.KundenIdValueObject;
import com.swt.fahrradshop.bestellung.valueObject.ZahlungValueObject;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

@Aggregate
public class BestellungAggregate {

    @AggregateIdentifier
    private String bestellungId;
    private BestellungsstatusEnum bestellungsstatusEnum;
    private KundenIdValueObject kundenIdValueObject;
    public BestellungAggregate() {
    }

    @CommandHandler
    public BestellungAggregate(CreateBestellungCommand cmd) {
            //TODO -- Chaouite: validate create Bestellung Command

        BestellungCreatedEvent evt = BestellungCreatedEvent.builder()
                .bestellungId(cmd.getBestellungId())
                .bestellungsstatus(cmd.getBestellungsstatusEnum())
                .kundenIdValueObject(cmd.getKundenIdValueObject())
                .build();

        AggregateLifecycle.apply(evt);
    }

    @EventSourcingHandler
    public void on(BestellungCreatedEvent evt) throws Exception{
        this.bestellungId= evt.getBestellungId();
        this.bestellungsstatusEnum = evt.getBestellungsstatus();
        this.kundenIdValueObject= evt.getKundenIdValueObject();
    }
}
