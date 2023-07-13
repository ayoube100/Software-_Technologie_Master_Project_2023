package com.swt.fahrradshop.katalog.repository;

import com.swt.fahrradshop.katalog.entity.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduktRepository extends JpaRepository<Produkt, String> {

    Produkt findProduktById(String produktId);
}
