package com.swt.fahrradshop.bestellung.query;

import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;

import java.math.BigDecimal;

public class FindBestellungenQuery {
    private  String bestellungId;

    private String bestellungsstatus;
    private String kundeId;
    private String warenkorbId;
    private BigDecimal gesamtpreis;
}
