package com.swt.fahrradshop.bestellung.valueObject;

import lombok.Data;

import javax.persistence.Embeddable;


@Embeddable
@Data
public class EinzelpostenValueObject {

    private Integer anzahl;
    //TODO -- till Ayoub creates it
    private String produktId;
}
