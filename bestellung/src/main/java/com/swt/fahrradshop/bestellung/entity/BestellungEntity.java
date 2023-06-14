package com.swt.fahrradshop.bestellung.entity;

import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import com.swt.fahrradshop.bestellung.valueObject.EinzelpostenValueObject;
import com.swt.fahrradshop.bestellung.valueObject.KundenIdValueObject;
import com.swt.fahrradshop.bestellung.valueObject.ZahlungValueObject;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="bestellungen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BestellungEntity {
    @Id
    @Column(unique = true)

    private String bestellungId;
    private BestellungsstatusEnum bestellungsstatus;
    private KundenIdValueObject kundenIdValueObject;

    @ElementCollection
    private List<EinzelpostenValueObject> einzelposten;

    private ZahlungValueObject zahlungValueObject;


  /*  //because JPA doesn't support non-Entity Objects directly
    @ElementCollection
    private List<EinzelpostenValueObject> einzelposten;


    @Embedded
    private ZahlungValueObject zahlungValueObject;*/
}
