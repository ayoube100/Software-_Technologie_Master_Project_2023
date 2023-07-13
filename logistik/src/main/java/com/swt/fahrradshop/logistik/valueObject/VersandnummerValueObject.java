package com.swt.fahrradshop.logistik.valueObject;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class VersandnummerValueObject {
    private String trackingNummer;
}
