package com.swt.fahrradshop.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindZahlungStatusByIdQuery {
    private String zahlungId;
}
