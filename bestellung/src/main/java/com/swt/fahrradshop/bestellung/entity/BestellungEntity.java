package com.swt.fahrradshop.bestellung.entity;

import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import com.swt.fahrradshop.bestellung.valueObject.EinzelpostenValueObject;
import com.swt.fahrradshop.bestellung.valueObject.KundenIdValueObject;
import com.swt.fahrradshop.bestellung.valueObject.ZahlungValueObject;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bestellungen")
public class BestellungEntity  {

    @Id
    private String bestellungId;

    private String bestellungsstatusEnum;

    private KundenIdValueObject kundenIdValueObject;

}
