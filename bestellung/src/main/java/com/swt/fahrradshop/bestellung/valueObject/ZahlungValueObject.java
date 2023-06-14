package com.swt.fahrradshop.bestellung.valueObject;

import lombok.Data;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class ZahlungValueObject {

    private Integer kreditkartennummer;
    private String ablaufdatum;
    private ZahlungsstatusEnum zahlungsstatusEnum;
    private String zahlungsart;
}
