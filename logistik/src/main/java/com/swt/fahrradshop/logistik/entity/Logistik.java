package com.swt.fahrradshop.logistik.entity;

import com.swt.fahrradshop.logistik.valueObject.*;
import lombok.*;


import javax.persistence.*;

import org.springframework.stereotype.Indexed;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Logistik {
    @Id
    private String logistikId;
    
    
}
