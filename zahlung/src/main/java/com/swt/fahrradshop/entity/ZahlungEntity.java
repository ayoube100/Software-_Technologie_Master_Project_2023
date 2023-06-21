package com.swt.fahrradshop.entity;

import com.swt.fahrradshop.valueObject.KreditKarte;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "zahlungen")
public class ZahlungEntity {
    @Id
    private String zahlungId;

    private String bestellungId;
    private BigDecimal gesamtpreis;

    @Embedded
    private KreditKarte kreditKarte;
    private String zahlungsstatus;
}
