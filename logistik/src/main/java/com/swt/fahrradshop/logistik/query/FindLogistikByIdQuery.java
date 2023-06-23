package com.swt.fahrradshop.logistik.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindLogistikByIdQuery {
    private String logistikToBeFoundId;
}
