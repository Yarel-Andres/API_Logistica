package com.yarel.logistica_api.Mapper;

import com.yarel.logistica_api.DTO.HistorialDTO;
import com.yarel.logistica_api.DTO.PaqueteCrearDTO;
import com.yarel.logistica_api.DTO.PaqueteObtenerDTO;
import com.yarel.logistica_api.Model.Cliente;
import com.yarel.logistica_api.Model.Historial;
import com.yarel.logistica_api.Model.Paquete;

public class Mapper {

    public static Paquete toEntity(PaqueteCrearDTO dto) {

        if (dto == null) return null;

        return Paquete.builder()
                .cliente(Cliente.builder()
                        .id(dto.getIdCliente())
                        .nombre(dto.getClienteNombre())
                        .apellido(dto.getClienteApellido())
                        .dni(dto.getClienteDni())
                        .build())
                .descripcion(dto.getDescripcion())
                .peso(dto.getPeso())
                .direccionDestino(dto.getDireccionDestino())
                .build();

    }


    public static PaqueteObtenerDTO toDTO(Paquete p) {

        if (p == null) return null;

        return PaqueteObtenerDTO.builder()
                .idPaquete(p.getId())
                .descripcion(p.getDescripcion())
                .peso(p.getPeso())
                .direccionDestino(p.getDireccionDestino())
                .codigoRastreo(p.getCodigoRastreo())
                .ubicacionActual(p.getUbicacionActual())
                .estado(p.getEstado())
                .idCliente(p.getCliente().getId())
                .nombreCompletoCliente(p.getCliente().getNombre() + " " + p.getCliente().getApellido())
                .nombreCompletoRepartidor(p.getRepartidor() != null ? p.getRepartidor().getNombre() + " " + p.getRepartidor().getApellido() : null)
                .build();
    }


    public static HistorialDTO toRecord(Historial h) {

        if (h == null) return null;

        return HistorialDTO.builder()
                .codigoRastreo(h.getPaquete().getCodigoRastreo())
                .ubiActual(h.getUbiActual())
                .fechaHoraCambioEstado(h.getFechaHoraCambioEstado())
                .estado(h.getEstado())
                .build();

    }

}


