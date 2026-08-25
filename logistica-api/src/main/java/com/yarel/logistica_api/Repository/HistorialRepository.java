package com.yarel.logistica_api.Repository;

import com.yarel.logistica_api.Model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {
    List<Historial> findByPaqueteIdOrderByFechaHoraCambioEstadoAsc(Long idPaquete);
}
