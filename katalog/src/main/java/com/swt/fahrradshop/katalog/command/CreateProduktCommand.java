package com.swt.fahrradshop.katalog.command;

import com.swt.fahrradshop.katalog.valueObject.Kategorie;
import com.swt.fahrradshop.katalog.valueObject.Verfuegbarkeit;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateProduktCommand {

    @TargetAggregateIdentifier
    private String produktId;
    private String Name;
    private BigDecimal Preis;
    private BigDecimal Anzahl;
    private Kategorie Kategorie;
    private Verfuegbarkeit Verfuegbarkeit;
    private BigDecimal AnzahlToReserve;
}
