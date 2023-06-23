package com.swt.fahrradshop.core.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import valueObject.KreditKarte;
import valueObject.ZahlungsstatusEnum;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessZahlungCommand {
    @TargetAggregateIdentifier
    private  String zahlungId;

    private  String bestellungId;
    private  BigDecimal gesamtpreis;
    private  KreditKarte kreditKarte;
    private  ZahlungsstatusEnum zahlungsstatusEnum;
}
