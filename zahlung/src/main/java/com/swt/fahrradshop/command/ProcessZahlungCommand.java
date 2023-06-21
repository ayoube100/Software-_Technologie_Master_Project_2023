package com.swt.fahrradshop.command;

import com.swt.fahrradshop.valueObject.KreditKarte;
import com.swt.fahrradshop.valueObject.ZahlungsstatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ProcessZahlungCommand {

    @TargetAggregateIdentifier
    private final String zahlungId;


    private final String bestellungId;
    private final BigDecimal gesamtpreis;
    private final KreditKarte kreditKarte;
    private final ZahlungsstatusEnum zahlungsstatusEnum;
}
