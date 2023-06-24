package com.swt.fahrradshop.logistik.event;

import com.swt.fahrradshop.logistik.valueObject.LieferstatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SendShippingEvent {
    private String logistikId;
    private final LieferstatusEnum lieferstatusEnum;
}
