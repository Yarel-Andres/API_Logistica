package com.yarel.logistica_api.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaqueteCrearDTO {

    @Schema(hidden = true)
    private Long idCliente;

    @Schema(example = "Yarel Andrés")
    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String clienteNombre;

    @Schema(example = "Sánchez Ramos")
    @NotBlank(message = "El apellido del cliente es obligatorio")
    private String clienteApellido;

    @Schema(example = "12345678Z")
    @NotBlank(message = "El DNI del cliente es obligatorio")
    private String clienteDni;


    @Schema(example = "Caja con componentes de PC")
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @Schema(example = "2.4")
    @Positive(message = "El peso debe ser mayor a cero")
    private Double peso;

    @Schema(example = "Calle Gran Vía 45, Madrid")
    @NotBlank(message = "La dirección de destino es obligatoria")
    private String direccionDestino;
}


