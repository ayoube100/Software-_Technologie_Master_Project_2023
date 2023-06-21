package com.swt.fahrradshop.logistik.valueObject;

//TODO: import und List Produkt anpassen
//import com.swt.fahrradshop.produkt

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LagerortType {
    
    private String lagerortId; 
    private String standort;
   // private List<Produkt> lagerbestand; 
}
