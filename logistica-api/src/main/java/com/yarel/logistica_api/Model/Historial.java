package com.yarel.logistica_api.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paquete_id", nullable = false)
    private Paquete paquete;

    private String ubiActual;
    private LocalDateTime fechaHoraCambioEstado;

    @Enumerated(EnumType.STRING)
    private EstadoPaquete estado;
}
