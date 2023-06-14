package com.swt.fahrradshop.bestellung.command;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import com.swt.fahrradshop.bestellung.valueObject.EinzelpostenValueObject;
import com.swt.fahrradshop.bestellung.valueObject.KundenIdValueObject;
import com.swt.fahrradshop.bestellung.valueObject.ZahlungValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class CreateBestellungCommand {
    //final because it's a read only class
    @TargetAggregateIdentifier
    private  final String bestellungId;
    
    private  final BestellungsstatusEnum bestellungsstatusEnum;
    private final KundenIdValueObject kundenIdValueObject;
}
