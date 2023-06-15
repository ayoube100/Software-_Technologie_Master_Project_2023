package com.swt.fahrradshop.bestellung.valueObject;

import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

//TODO -- Ayoub: get Produkt data
@Data
@Embeddable
public class Produkt {
    private String produktId;
    private BigDecimal produktPreis;
    private Integer produktAnzahl;
}
