package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.model.dto.TipoItemsDto;
import com.proyecto.progra.backend.model.entity.TipoItems;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.ITipoItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v7")
@CrossOrigin(origins = "*")
public class TipoItemsController {

    @Autowired
    private ITipoItems tipoItemsService;

    @GetMapping("tipo-items/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        TipoItems tipoItems = tipoItemsService.findById(id);
        if(tipoItems==null){
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
                        .object(tipoItems)
                        .build()
                ,HttpStatus.OK);
        }

    @GetMapping("tipos-items")
    public ResponseEntity<?> findAll() {
        try {
            List<TipoItems> tiposItems = tipoItemsService.findAll();
            List<TipoItemsDto> tiposItemsDto = tiposItems.stream()
                    .map(tipoItems -> TipoItemsDto.builder()
                            .id(tipoItems.getId())
                            .descripcion(tipoItems.getDescripcion())
                            .tipoExamen(tipoItems.getIdTipoExamen())
                            .build())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(tiposItemsDto, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener la lista de tipos de items: " + exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }


    }
