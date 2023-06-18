package com.swt.fahrradshop.bestellung.entity;

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

    private String bestellungsstatus;
    private String kundeId;
    private String warenkorbId;
    private BigDecimal gesamtpreis;
}
