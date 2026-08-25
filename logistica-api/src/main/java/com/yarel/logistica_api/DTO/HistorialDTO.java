package com.yarel.logistica_api.DTO;

import com.yarel.logistica_api.Model.EstadoPaquete;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialDTO {

    private String codigoRastreo;

    private String ubiActual;
    private LocalDateTime fechaHoraCambioEstado;
    private EstadoPaquete estado;
}
