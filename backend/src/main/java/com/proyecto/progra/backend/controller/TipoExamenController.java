package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.model.dto.TipoExamenDto;
import com.proyecto.progra.backend.model.entity.TipoExamen;
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
@RequestMapping("api/v6")
@CrossOrigin(origins = "*")
public class TipoExamenController {

    @Autowired
    private ITipoExamen tipoExamenService;
    /*
    @PostMapping("tipo-examen")
    public ResponseEntity<?> create(@RequestBody TipoExamenDto tipoExamenDto){
        TipoExamen tipoExamenSave = null;
        try {
            tipoExamenSave = tipoExamenService.save(tipoExamenDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(TipoExamenDto.builder()
                            .id(tipoExamenSave.getId())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("tipo-examen/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody TipoExamenDto tipoExamenDto, @PathVariable Integer id){
        TipoExamen tipoExamenUpdate = null;
        try {
            if(tipoExamenService.existById(id)){
                tipoExamenDto.setId(id);
                tipoExamenUpdate = tipoExamenService.save(tipoExamenDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Guardado correctamente")
                                .object(TipoExamenDto.builder()
                                        .id(tipoExamenUpdate.getId())
                                        .build())
                                .build()
                        ,HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>
                        (MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build(),HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping ("tipo-examen/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            TipoExamen tipoExamenDelete  = tipoExamenService.findById(id);
            tipoExamenService.delete(tipoExamenDelete);
            return new ResponseEntity<>(tipoExamenDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
     */

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
                                .id(Integer.valueOf(tipoExamen.getNombre()))
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
                            .id(Integer.valueOf(tipoExamen.getNombre()))
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
