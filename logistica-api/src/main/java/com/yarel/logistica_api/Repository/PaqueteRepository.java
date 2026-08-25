package com.yarel.logistica_api.Repository;

import com.yarel.logistica_api.Model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete, Long> {
    Optional<Paquete> findByCodigoRastreo(String codigoRastreo);
    List<Paquete> findByClienteDni(String dni);
}
