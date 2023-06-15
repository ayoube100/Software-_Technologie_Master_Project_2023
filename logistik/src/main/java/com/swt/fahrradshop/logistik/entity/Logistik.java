package com.swt.fahrradshop.logistik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Indexed;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Logistik {
    @Id
    private UUID logistikId;
    
    
}
