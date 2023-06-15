package com.swt.fahrradshop.bestellung.entity;

import com.swt.fahrradshop.bestellung.valueObject.*;
import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bestellungen")
public class BestellungEntity  {

    @Id
    private String bestellungId;

    private String bestellungsstatusEnum;

    private KundeIdValueObject kundeIdValueObject;
    private String warenkorbId;
    private BigDecimal gesamtpreis;
}
