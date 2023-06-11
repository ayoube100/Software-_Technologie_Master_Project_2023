package com.swt.fahrradshop.katalog.model;

import com.swt.fahrradshop.katalog.valueObject.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProduktModel {
    private int id;
    private nameType name;
    private beschreibungType Beschreibung;
    private preisType preis;
    private KategorieEnum kategorie;
    private verfuegbarkeitType verfügbarkeit;


}
