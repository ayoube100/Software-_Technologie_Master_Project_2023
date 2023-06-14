package com.swt.fahrradshop.bestellung.aggregate;

import com.swt.fahrradshop.bestellung.command.CreateBestellungCommand;
import com.swt.fahrradshop.bestellung.event.BestellungCreatedEvent;
import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import com.swt.fahrradshop.bestellung.valueObject.EinzelpostenValueObject;
import com.swt.fahrradshop.bestellung.valueObject.KundenIdValueObject;
import com.swt.fahrradshop.bestellung.valueObject.ZahlungValueObject;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

@Slf4j
@Aggregate
public class BestellungAggregate {

    @AggregateIdentifier
    private String bestellungId;
    private BestellungsstatusEnum bestellungsstatus;
    private List<EinzelpostenValueObject> einzelposten;
    private KundenIdValueObject kundenIdValueObject;
    private ZahlungValueObject zahlungValueObject;
    public BestellungAggregate() {
    }
    @CommandHandler
    public BestellungAggregate(CreateBestellungCommand cmd) {
            //TODO -- Chaouite: validate create Bestellung Command

        BestellungCreatedEvent evt = BestellungCreatedEvent.builder()
                .bestellungId(cmd.getBestellungId())
                .bestellungsstatus(cmd.getBestellungsstatus())
                .einzelposten(cmd.getEinzelposten())
                .kundenIdValueObject(cmd.getKundenIdValueObject())
                .zahlungValueObject(cmd.getZahlungValueObject())
                .build();

        AggregateLifecycle.apply(evt);
    }

    @EventSourcingHandler
    public void on(BestellungCreatedEvent evt){
        this.bestellungId= evt.getBestellungId();
        this.bestellungsstatus = evt.getBestellungsstatus();
        this.einzelposten = evt.getEinzelposten();
        this.kundenIdValueObject= evt.getKundenIdValueObject();
        this.zahlungValueObject = evt.getZahlungValueObject();
    }
}
