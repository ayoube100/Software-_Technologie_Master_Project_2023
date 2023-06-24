package com.swt.fahrradshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.swt.fahrradshop.core.valueObject.KreditKarte;

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
