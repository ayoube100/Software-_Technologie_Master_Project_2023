package com.swt.fahrradshop.logistik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "logistik")

public class LogistikEntity {
    @Id
    private String logistikId;

    private String bestellungId;
    private String lieferstatus;


}
