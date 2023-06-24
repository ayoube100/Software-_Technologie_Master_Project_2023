package com.swt.fahrradshop.logistik.event;

import com.swt.fahrradshop.logistik.valueObject.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogistikCreatedEvent {
    @TargetAggregateIdentifier
    private String logistikId;

    private String bestellungId;
    private LieferstatusEnum lieferstatus;
// private LagerortValueObject lagerortValueObject;;
//  private VersandnummerValueObject versandnummerValueObject;
//  private LieferadresseValueObject lieferadresseValueObject;
}
