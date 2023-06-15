package com.swt.fahrradshop.bestellung.model;

import com.swt.fahrradshop.bestellung.valueObject.Produkt;

import java.util.List;

public class WarenkorbModel {

    private String kundeId;
    //can be taken because a Warenkorb is created always at the beginning without any Produkte
    private List<Produkt> produkteList;

}
