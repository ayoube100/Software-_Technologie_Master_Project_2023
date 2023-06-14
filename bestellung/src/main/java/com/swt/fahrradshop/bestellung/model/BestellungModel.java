package com.swt.fahrradshop.bestellung.model;

import com.swt.fahrradshop.bestellung.valueObject.KundenIdValueObject;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BestellungModel {

    private KundenIdValueObject kundenIdValueObject;
    private String warenkorbId;
    private BigDecimal gesamtpreis;
}
