package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.model.entity.TipoSolicitante;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.ITipoSolicitante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TipoSolicitanteController {

    @Autowired
    private ITipoSolicitante tipoSolicitanteService;

    @GetMapping("tipos-solicitantes/all")
    public ResponseEntity<?> findAll() {
        try {
            List<TipoSolicitante> tipoSolicitantes = tipoSolicitanteService.findAll();
            List<TipoSolicitante> tiposSolicitante = tipoSolicitantes.stream()
                    .map(tipoSolicitante -> TipoSolicitante.builder()
                            .id(tipoSolicitante.getId())
                            .descripcion(tipoSolicitante.getDescripcion())

                            .build())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(tiposSolicitante, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener la lista de Tipos Soportes: " + exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("tipo-solicitante/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        TipoSolicitante tipoSolicitante = tipoSolicitanteService.findById(id);
        if(tipoSolicitante==null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta Exitosa")
                        .object(TipoSolicitante.builder()
                                .id(tipoSolicitante.getId())
                                .descripcion(tipoSolicitante.getDescripcion())
                                .build())
                        .build()
                ,HttpStatus.OK);
    }
}
