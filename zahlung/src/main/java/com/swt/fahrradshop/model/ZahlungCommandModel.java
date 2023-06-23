package com.swt.fahrradshop.model;

import valueObject.KreditKarte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZahlungCommandModel {


    private String bestellungId;
    private BigDecimal gesamtpreis;
    private KreditKarte kreditKarte;
}
