package com.yarel.logistica_api.DTO;

import com.yarel.logistica_api.Model.EstadoPaquete;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaqueteActualizarEstadoDTO {

    @NotNull(message = "El estado no puede estar vacío")
    private EstadoPaquete nuevoEstado;

    @NotBlank(message = "La ubicación actual no puede estar vacía")
    private String ubicacionActual;
}
