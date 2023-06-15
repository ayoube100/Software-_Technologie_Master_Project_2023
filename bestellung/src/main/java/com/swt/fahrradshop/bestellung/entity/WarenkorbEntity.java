package com.swt.fahrradshop.bestellung.entity;

import com.swt.fahrradshop.bestellung.valueObject.Produkt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="Warenkoerbe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarenkorbEntity {
    @Id
    private String warenkorbId;

    private String kundeId;

    @ElementCollection
    private List<Produkt> produkteList;

    private BigDecimal gesamtpreis;

}
