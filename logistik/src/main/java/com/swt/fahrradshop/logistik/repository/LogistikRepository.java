package com.swt.fahrradshop.logistik.repository;
import java.util.UUID;
import com.swt.fahrradshop.logistik.entity.Logistik;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LogistikRepository extends JpaRepository<Logistik, UUID> {
}
