package com.swt.fahrradshop.bestellung.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestellungQueryModel {

    private  String bestellungId;

    private String bestellungsstatus;
    private String kundeId;
    private String warenkorbId;
    private BigDecimal gesamtpreis;
}
