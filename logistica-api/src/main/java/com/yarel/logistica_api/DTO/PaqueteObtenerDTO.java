package com.yarel.logistica_api.DTO;

import com.yarel.logistica_api.Model.EstadoPaquete;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaqueteObtenerDTO {

    private Long idPaquete;
    private String descripcion;
    private Double peso;
    private String direccionDestino;
    private String codigoRastreo;
    private String ubicacionActual;


    private EstadoPaquete estado;


    private Long idCliente;
    private String nombreCompletoCliente;


    private String nombreCompletoRepartidor;
}
