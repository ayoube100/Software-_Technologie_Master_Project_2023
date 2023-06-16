package com.swt.fahrradshop.bestellung.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BestellungCommandModel {

    private String kundeId;
    private String warenkorbId;
    private BigDecimal gesamtpreis;
}
