package com.swt.fahrradshop.bestellung.event;

import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import com.swt.fahrradshop.bestellung.valueObject.EinzelpostenValueObject;
import com.swt.fahrradshop.bestellung.valueObject.KundenIdValueObject;
import com.swt.fahrradshop.bestellung.valueObject.ZahlungValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestellungCreatedEvent {

    private  String bestellungId;
    private  BestellungsstatusEnum bestellungsstatus;
    private  KundenIdValueObject kundenIdValueObject;
}
