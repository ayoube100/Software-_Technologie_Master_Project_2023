package com.swt.fahrradshop.model;

import valueObject.KreditKarte;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ZahlungQueryModel {
    private String zahlungId;
    private String bestellungId;
    private BigDecimal gesamtpreis;
    private KreditKarte kreditKarte;
    private String zahlungsstatusEnum;
}
