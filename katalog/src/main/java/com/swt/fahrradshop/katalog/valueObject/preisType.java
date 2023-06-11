package com.swt.fahrradshop.katalog.valueObject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class preisType {
    private enum Waehrung {
        EURO,
        MAD
    }

    private BigDecimal preis;
}
