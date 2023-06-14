package com.swt.fahrradshop.bestellung.valueObject;

import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Data
public class WarenkorbValueObject {
    private String warenkorbId;
    private BigDecimal gesamtePreis;
}
