package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.model.dto.TipoExamenDto;
import com.proyecto.progra.backend.model.entity.TipoExamen;
import com.proyecto.progra.backend.model.entity.TipoItems;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.ITipoExamen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class TipoExamenController {

    @Autowired
    private ITipoExamen tipoExamenService;

    @GetMapping ("tipo-examen/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        TipoExamen tipoExamen = tipoExamenService.findById(id);
        if(tipoExamen==null){
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
                        .object(TipoExamenDto.builder()
                                .id(tipoExamen.getId())
                                .nombre(tipoExamen.getNombre())
                                .descripcion(tipoExamen.getDescripcion())
                                .itemsList(tipoExamen.getTipoItemsList())
                                .build())
                        .build()
                ,HttpStatus.OK);
    }

    @GetMapping("tipos-examenes")
    public ResponseEntity<?> findAll() {
        try {
            List<TipoExamen> tiposExamenes = tipoExamenService.findAll();
            List<TipoExamenDto> tiposExamenesDto = tiposExamenes.stream()
                    .map(tipoExamen -> TipoExamenDto.builder()
                            .id(tipoExamen.getId())
                            .nombre(tipoExamen.getNombre())
                            .descripcion(tipoExamen.getDescripcion())
                            .itemsList(tipoExamen.getTipoItemsList())
                            .build())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(tiposExamenesDto, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener la lista de tipos de examenes: " + exDt.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
