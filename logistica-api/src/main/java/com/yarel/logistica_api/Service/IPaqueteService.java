package com.yarel.logistica_api.Service;

import com.yarel.logistica_api.DTO.HistorialDTO;
import com.yarel.logistica_api.DTO.PaqueteCrearDTO;
import com.yarel.logistica_api.DTO.PaqueteObtenerDTO;
import com.yarel.logistica_api.Model.EstadoPaquete;

import java.util.List;

public interface IPaqueteService {

    List<PaqueteObtenerDTO> traerPaquetes();

    List<PaqueteObtenerDTO> traerPaquetesPorDni(String Dni);

    PaqueteObtenerDTO crearPaquete(PaqueteCrearDTO dto);

    PaqueteObtenerDTO paquetePorCodigoRastreo(String codigoRastreo);

    PaqueteObtenerDTO asignarRepartidor(Long idPaquete, Long idRepartidor);

    PaqueteObtenerDTO actualizarEstado(Long idPaquete, EstadoPaquete nuevoEstado, String ubicacionActual);

    List<HistorialDTO> historialPorCodigo(String codigoRastreo);
}
