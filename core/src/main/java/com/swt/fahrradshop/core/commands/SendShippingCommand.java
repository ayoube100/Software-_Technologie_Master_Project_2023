package com.swt.fahrradshop.core.commands;

import com.swt.fahrradshop.core.valueObject.LieferstatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Data
@Builder
@AllArgsConstructor
public class SendShippingCommand {
    @TargetAggregateIdentifier
    private final String logistikId;
    private String bestellungId;
    private LieferstatusEnum lieferstatusEnum;
}
