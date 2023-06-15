package com.swt.fahrradshop.logistik.valueObject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class versandnummerType {
    private UUID trackingNummer;
}
