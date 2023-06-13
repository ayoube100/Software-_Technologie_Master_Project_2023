package com.swt.fahrradshop.bestellung.model;

import com.swt.fahrradshop.bestellung.valueObject.*;
import lombok.Data;

import java.util.List;

@Data

public class BestellungModel {


    private String bestellungId;
    private BestellungsstatusEnum bestellungsstatus;
    private List<EinzelpostenValueObject> einzelposten;
    private KundenIdValueObject kundenIdValueObject;
    private ZahlungValueObject zahlung;
}
