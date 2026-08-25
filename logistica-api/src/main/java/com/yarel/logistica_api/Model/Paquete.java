package com.yarel.logistica_api.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Double peso;
    private String direccionDestino;
    private String codigoRastreo;
    private String ubicacionActual;

    @Enumerated(EnumType.STRING)
    private EstadoPaquete estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "repartidor_id", nullable = true)
    private Repartidor repartidor;
}
