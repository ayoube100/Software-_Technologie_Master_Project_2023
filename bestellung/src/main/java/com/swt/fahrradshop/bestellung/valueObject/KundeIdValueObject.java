package com.swt.fahrradshop.bestellung.valueObject;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
//TODO - till the Kunden service is created
public class KundeIdValueObject {
    private String kundenId;
}
