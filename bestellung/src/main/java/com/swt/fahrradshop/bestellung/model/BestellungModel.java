package com.swt.fahrradshop.bestellung.model;

import com.swt.fahrradshop.bestellung.valueObject.BestellungsstatusEnum;
import com.swt.fahrradshop.bestellung.valueObject.KundenIdValueObject;
import lombok.Data;

@Data
public class BestellungModel {

    private KundenIdValueObject kundenIdValueObject;
}
