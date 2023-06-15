package com.swt.fahrradshop.bestellung.model;

import com.swt.fahrradshop.bestellung.valueObject.KundeIdValueObject;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BestellungModel {

    private KundeIdValueObject kundeIdValueObject;
    private String warenkorbId;
    private BigDecimal gesamtpreis;
}
