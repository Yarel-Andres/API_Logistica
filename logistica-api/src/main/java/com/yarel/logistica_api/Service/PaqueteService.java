package com.yarel.logistica_api.Service;

import com.yarel.logistica_api.DTO.HistorialDTO;
import com.yarel.logistica_api.DTO.PaqueteCrearDTO;
import com.yarel.logistica_api.DTO.PaqueteObtenerDTO;
import com.yarel.logistica_api.Mapper.Mapper;
import com.yarel.logistica_api.Model.*;
import com.yarel.logistica_api.Repository.ClienteRepository;
import com.yarel.logistica_api.Repository.HistorialRepository;
import com.yarel.logistica_api.Repository.PaqueteRepository;
import com.yarel.logistica_api.Repository.RepartidorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaqueteService implements IPaqueteService {

    private final PaqueteRepository repoPaq;
    private final RepartidorRepository repoRep;
    private final HistorialRepository repoHis;
    private final ClienteRepository repoCli;


    @Override
    public List<PaqueteObtenerDTO> traerPaquetes() {
        return repoPaq.findAll().stream().map(Mapper::toDTO).toList();
    }


    @Override
    public List<PaqueteObtenerDTO> traerPaquetesPorDni(String dni) {

        repoCli.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("El DNI " + dni + " no corresponde a ningún cliente registrado"));

        return repoPaq.findByClienteDni(dni)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }


    @Override
    public PaqueteObtenerDTO crearPaquete(PaqueteCrearDTO dto) {

        Paquete paquete = Mapper.toEntity(dto);

        int numeroAleatorio = new java.util.Random().nextInt(900000) + 100000;
        paquete.setCodigoRastreo("ENV-" + numeroAleatorio);

        Cliente cliente = repoCli.findByDni(dto.getClienteDni())
                .orElseGet(() -> {
                    Cliente nuevoCliente = Cliente.builder()
                            .id(dto.getIdCliente())
                            .nombre(dto.getClienteNombre())
                            .apellido(dto.getClienteApellido())
                            .dni(dto.getClienteDni())
                            .build();
                    return repoCli.save(nuevoCliente);
                });

        paquete.setCliente(cliente);

        Paquete paqueteGuardado = repoPaq.save(paquete);

        return actualizarEstado(paqueteGuardado.getId(), EstadoPaquete.RECIBIDO, "Oficina de Origen"
        );
    }


    @Override
    public PaqueteObtenerDTO paquetePorCodigoRastreo(String codigoRastreo) {

        Paquete paquete = repoPaq.findByCodigoRastreo(codigoRastreo)
                .orElseThrow(() -> new RuntimeException("Código de rastreo " + codigoRastreo + " no válido"));

        return Mapper.toDTO(paquete);
    }


    @Override
    public PaqueteObtenerDTO asignarRepartidor(Long idPaquete, Long idRepartidor) {

        Paquete paquete = repoPaq.findById(idPaquete)
                .orElseThrow(() -> new RuntimeException("Id de Paquete " + idPaquete + " no válido"));

        Repartidor repartidor = repoRep.findById(idRepartidor)
                .orElseThrow(() -> new RuntimeException("Id de Repartidor " + idRepartidor + " no válido"));

        paquete.setRepartidor(repartidor);

        return actualizarEstado(idPaquete, EstadoPaquete.EN_TRANSITO, "Oficina de Origen");
    }


    @Override
    public PaqueteObtenerDTO actualizarEstado(Long idPaquete, EstadoPaquete estadoPaquete, String ubicacionActual) {

        Paquete paquete = repoPaq.findById(idPaquete)
                .orElseThrow(() -> new RuntimeException("Id de Paquete " + idPaquete + " no válido"));

        paquete.setEstado(estadoPaquete);
        paquete.setUbicacionActual(ubicacionActual);


        Historial historial = Historial.builder()
                .paquete(paquete)
                .ubiActual(ubicacionActual)
                .estado(estadoPaquete)
                .fechaHoraCambioEstado(LocalDateTime.now())
                .build();

        repoHis.save(historial);

        return Mapper.toDTO(repoPaq.save(paquete));
    }


    @Override
    public List<HistorialDTO> historialPorCodigo(String codigoRastreo) {

        Paquete paquete = repoPaq.findByCodigoRastreo(codigoRastreo)
                .orElseThrow(() -> new RuntimeException("Código de rastreo " + codigoRastreo + " no válido"));

        return repoHis.findByPaqueteIdOrderByFechaHoraCambioEstadoAsc(paquete.getId())
                .stream()
                .map(Mapper::toRecord)
                .toList();
    }
}
