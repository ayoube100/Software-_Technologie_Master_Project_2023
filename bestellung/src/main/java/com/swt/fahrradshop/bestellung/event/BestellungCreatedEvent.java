package com.swt.fahrradshop.bestellung.event;

import com.swt.fahrradshop.bestellung.valueObject.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestellungCreatedEvent {

   // @TargetAggregateIdentifier
    private  String bestellungId;

    private BestellungsstatusEnum bestellungsstatus;
    private String kundeId;
    private String warenkorbId;
    private BigDecimal gesamtpreis;
}
