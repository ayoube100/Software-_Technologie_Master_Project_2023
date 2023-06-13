package com.swt.fahrradshop.bestellung.event;

import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import com.swt.fahrradshop.bestellung.valueObject.EinzelpostenValueObject;
import com.swt.fahrradshop.bestellung.valueObject.KundenIdValueObject;
import com.swt.fahrradshop.bestellung.valueObject.ZahlungValueObject;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@Builder
public class BestellungCreatedEvent {

    @TargetAggregateIdentifier
    private String bestellungId;
    private BestellungsstatusEnum bestellungsstatus;
    private List<EinzelpostenValueObject> einzelposten;
    private KundenIdValueObject kundenIdValueObject;
    private ZahlungValueObject zahlung;
}
