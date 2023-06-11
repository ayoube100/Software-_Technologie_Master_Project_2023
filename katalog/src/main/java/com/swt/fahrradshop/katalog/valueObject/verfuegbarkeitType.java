package com.swt.fahrradshop.katalog.valueObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class verfuegbarkeitType{
        private enum VerfuegbarkeitEnum {
                IN_STOCK,
                OUT_OF_STOCK
        }
        private Integer bestand;
        //To be later reviewed
        private  Integer mindestBestand;
}
