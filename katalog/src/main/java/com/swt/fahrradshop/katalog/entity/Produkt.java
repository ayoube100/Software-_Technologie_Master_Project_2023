package com.swt.fahrradshop.katalog.entity;

import com.swt.fahrradshop.katalog.valueObject.Kategorie;
import com.swt.fahrradshop.katalog.valueObject.Verfuegbarkeit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produkt {
    @Id
    private UUID ProduktId;
    private String Name;
    private BigDecimal Preis;
    private BigDecimal Anzahl;

    //@Column(name = "Kategorie")
    @Enumerated(EnumType.STRING)
    private Kategorie Kategorie;
    @Enumerated(EnumType.STRING)
    private Verfuegbarkeit Verfuegbarkeit;



}

