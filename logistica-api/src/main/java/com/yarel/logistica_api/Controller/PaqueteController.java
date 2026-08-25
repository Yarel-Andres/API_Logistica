package com.yarel.logistica_api.Controller;

import com.yarel.logistica_api.DTO.HistorialDTO;
import com.yarel.logistica_api.DTO.PaqueteActualizarEstadoDTO;
import com.yarel.logistica_api.DTO.PaqueteCrearDTO;
import com.yarel.logistica_api.DTO.PaqueteObtenerDTO;
import com.yarel.logistica_api.Service.IPaqueteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/paquetes")
public class PaqueteController {

    private final IPaqueteService paqueteService;

    @PostMapping
    public ResponseEntity<PaqueteObtenerDTO> crearPaquete(@Valid @RequestBody PaqueteCrearDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paqueteService.crearPaquete(dto));
    }

    @GetMapping
    public ResponseEntity<List<PaqueteObtenerDTO>> traerPaquetes() {
        return ResponseEntity.ok(paqueteService.traerPaquetes());
    }

    @GetMapping("dni/{dni}")
    public ResponseEntity<List<PaqueteObtenerDTO>> traerPaquetesPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(paqueteService.traerPaquetesPorDni(dni));
    }

    @GetMapping("rastreo/{codigoRastreo}")
    public ResponseEntity<PaqueteObtenerDTO> traerPaquetePorCodigoRastreo(@PathVariable String codigoRastreo) {
        return ResponseEntity.ok(paqueteService.paquetePorCodigoRastreo(codigoRastreo));
    }

    @PutMapping("asignarRepartidor/{idPaquete}/{idRepartidor}")
    public ResponseEntity<PaqueteObtenerDTO> asignarRepartidor(@PathVariable Long idPaquete, @PathVariable Long idRepartidor) {
        return ResponseEntity.ok(paqueteService.asignarRepartidor(idPaquete, idRepartidor));
    }

    @PutMapping("actualizarEstado/{idPaquete}/estado")
    public ResponseEntity<PaqueteObtenerDTO> actualizarEstado(@PathVariable Long idPaquete,
                                                              @Valid @RequestBody PaqueteActualizarEstadoDTO dto) {
        return ResponseEntity.ok(paqueteService.actualizarEstado(idPaquete, dto.getNuevoEstado(), dto.getUbicacionActual()));
    }

    @GetMapping("historial/{codigoRastreo}")
    public ResponseEntity<List<HistorialDTO>> traerHistorial(@PathVariable String codigoRastreo) {
        return ResponseEntity.ok(paqueteService.historialPorCodigo(codigoRastreo));
    }


}
