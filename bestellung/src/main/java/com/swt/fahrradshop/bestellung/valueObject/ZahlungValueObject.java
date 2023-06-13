package com.swt.fahrradshop.bestellung.valueObject;

import lombok.Data;

@Data
public class ZahlungValueObject {

    private Integer kreditkartennummer;
    private String ablaufdatum;
    private ZahlungsstatusEnum zahlungsstatusEnum;
    private String zahlungsart;
}
