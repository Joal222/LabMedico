package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.model.dto.ItemsDto;
import com.proyecto.progra.backend.model.entity.Items;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.IItems;
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
public class ItemsController {
    @Autowired
    private IItems itemsService;

    @DeleteMapping("items/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Items itemsDelete = itemsService.findById(id);
            itemsService.delete(itemsDelete);
            return new ResponseEntity<>(itemsDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("items/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Items items = itemsService.findById(id);
        if (items == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta Exitosa")
                        .object(ItemsDto.builder()
                                .id(items.getId())
                                //.idTipoItems(items.getIdTipoItems())
                                //.idSolicitudMuestraMedica(items.getIdSolicitudMuestraMedica())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

    @GetMapping("items/all")
    public ResponseEntity<?> findAll() {
        try {
            List<Items> itemsList = itemsService.findAll();
            List<ItemsDto> itemsDtoList = itemsList.stream()
                    .map(items -> ItemsDto.builder()
                            .id(items.getId())
                            //.idTipoItems(items.getIdTipoItems())
                            //.idSolicitudMuestraMedica(items.getIdSolicitudMuestraMedica())
                            .build())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(itemsDtoList, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener la lista de items: " + exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }

    }
}
