package com.swt.fahrradshop.logistik.event;

import com.swt.fahrradshop.logistik.valueObject.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogistikCreatedEvent {
    private String logistikId;
    private String bestellungId;
    private LieferstatusEnum lieferstatus;
    private LagerortType lagerortValueObject;;
    private VersandnummerType versandnummerValueObject;
    private LieferadresseType lieferadresseValueObject;
}
