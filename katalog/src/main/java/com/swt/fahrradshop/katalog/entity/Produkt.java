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
@Table(name = "produkt")
public class Produkt {
    @Id
    private UUID id;
    private String Name;
    private BigDecimal Preis;
    private BigDecimal Anzahl;

    // this will heplp to display the enum as a string in the DB
    @Enumerated(EnumType.STRING)
    private Kategorie Kategorie;
    @Enumerated(EnumType.STRING)
    private Verfuegbarkeit Verfuegbarkeit;



}

