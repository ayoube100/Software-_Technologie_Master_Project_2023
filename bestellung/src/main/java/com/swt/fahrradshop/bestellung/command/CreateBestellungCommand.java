package com.swt.fahrradshop.bestellung.command;
import com.swt.fahrradshop.bestellung.valueObject.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
public class CreateBestellungCommand {
    //final because it's a read only class
    @TargetAggregateIdentifier
    private  final String bestellungId;
    
    private  final BestellungsstatusEnum bestellungsstatusEnum;
    private final KundenIdValueObject kundenIdValueObject;
    private String warenkorbId;
    private BigDecimal gesamtpreis;

}
