package com.swt.fahrradshop.logistik.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogistikQueryModel {

    private  String logistikId;

    private String lieferstatus;
    private String bestellungId;
}
