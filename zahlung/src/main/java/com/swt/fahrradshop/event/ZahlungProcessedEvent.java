package com.swt.fahrradshop.event;

import com.swt.fahrradshop.valueObject.KreditKarte;
import com.swt.fahrradshop.valueObject.ZahlungsstatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZahlungProcessedEvent {
    private String zahlungId;
    private String bestellungId;
    private BigDecimal gesamtpreis;
    private KreditKarte kreditKarte;
    private ZahlungsstatusEnum zahlungsstatusEnum;
}
