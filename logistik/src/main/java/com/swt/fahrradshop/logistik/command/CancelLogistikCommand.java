package com.swt.fahrradshop.logistik.command;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.swt.fahrradshop.logistik.valueObject.LieferstatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class CancelLogistikCommand {
    //final because it's a read only class
    @TargetAggregateIdentifier
    private  final String logistikId;

    private final String bestellungId;
    private final LieferstatusEnum lierfserstatusEnum;
}
