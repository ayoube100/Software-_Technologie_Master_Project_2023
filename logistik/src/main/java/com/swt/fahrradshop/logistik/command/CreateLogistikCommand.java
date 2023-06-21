package com.swt.fahrradshop.logistik.command;

import com.swt.fahrradshop.logistik.valueObject.LieferstatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class CreateLogistikCommand {

    @TargetAggregateIdentifier
    private final String logistikId;
    private final LieferstatusEnum lieferstatusEnum;
}

