package com.proyecto.progra.backend.controller;
import com.proyecto.progra.backend.model.entity.TipoSoporte;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.ITipoSoporte;
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
public class TipoSoporteController {

    @Autowired
    private ITipoSoporte tipoSoporteService;

    @GetMapping("tipos-soportes/all")
    public ResponseEntity<?> findAll() {
        try {
            List<TipoSoporte> tipoSoportes = tipoSoporteService.findAll();
            List<TipoSoporte> tiposSoporte = tipoSoportes.stream()
                    .map(tipoSoporte -> TipoSoporte.builder()
                            .id(tipoSoporte.getId())
                            .descripcion(tipoSoporte.getDescripcion())

                            .build())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(tiposSoporte, HttpStatus.OK);
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

    @GetMapping("tipo-soporte/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        TipoSoporte tipoSoporte = tipoSoporteService.findById(id);
        if(tipoSoporte==null){
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
                        .object(TipoSoporte.builder()
                                .id(tipoSoporte.getId())
                                .descripcion(tipoSoporte.getDescripcion())
                                .build())
                        .build()
                ,HttpStatus.OK);
    }
}
