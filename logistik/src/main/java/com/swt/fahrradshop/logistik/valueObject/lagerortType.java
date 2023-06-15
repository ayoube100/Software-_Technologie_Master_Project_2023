package com.swt.fahrradshop.logistik.valueObject;

//TODO: import und List Produkt anpassen
//import com.swt.fahrradshop.produkt

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class lagerortType {
    
    private UUID id; 
    private String standort;
   // private List<Produkt> lagerbestand; 
}
