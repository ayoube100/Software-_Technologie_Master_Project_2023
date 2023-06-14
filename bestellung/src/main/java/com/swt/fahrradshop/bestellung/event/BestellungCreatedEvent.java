package com.swt.fahrradshop.bestellung.event;

import com.swt.fahrradshop.bestellung.valueObject.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestellungCreatedEvent {

    private  String bestellungId;
    private  BestellungsstatusEnum bestellungsstatus;
    private  KundenIdValueObject kundenIdValueObject;
    private String warenkorbId;
    private BigDecimal gesamtpreis;
}
