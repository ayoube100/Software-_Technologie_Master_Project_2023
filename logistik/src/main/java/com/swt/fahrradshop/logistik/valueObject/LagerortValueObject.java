package com.swt.fahrradshop.logistik.valueObject;

//TODO: import und List Produkt anpassen
//import com.swt.fahrradshop.produkt

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class LagerortValueObject {

    private String lagerortId;
    private String standort;
    // private List<Produkt> lagerbestand;
}
