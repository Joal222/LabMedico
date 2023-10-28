package com.proyecto.progra.backend.controller;
import com.proyecto.progra.backend.model.dto.MuestraItemsDto;
import com.proyecto.progra.backend.model.entity.MuestraItems;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.IMuestraItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v5")
@CrossOrigin(origins = "*")
public class MuestraItemsController {

    @Autowired
    private IMuestraItems muestraItemsService;
    @PostMapping("muestra/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody MuestraItemsDto muestraItemsDto){
        MuestraItems muestraItemsSave = null;
        try{
            muestraItemsSave = muestraItemsService.save(muestraItemsDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(MuestraItemsDto.builder()
                            .id(muestraItemsSave.getId())
                            .idMuestraMedica(muestraItemsSave.getIdMuestraMedica())
                            .idItems(muestraItemsSave.getIdItems())
                    .build())
                    .build()
                    ,HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping ("muestra/items/{id}")
    public ResponseEntity<?> update(@RequestBody MuestraItemsDto muestraItemsDto, @PathVariable Integer id) {
        MuestraItems muestraItemsUpdate = null ;
        try{
            if(muestraItemsService.existById(id)){
                muestraItemsDto.setId(id);
                muestraItemsUpdate = muestraItemsService.save(muestraItemsDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Guardado correctamente")
                                .object(MuestraItemsDto.builder()
                                        .id(muestraItemsUpdate.getId())
                                        .idMuestraMedica(muestraItemsUpdate.getIdMuestraMedica())
                                        .idItems(muestraItemsUpdate.getIdItems())
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
    @DeleteMapping ("muestra/items/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            MuestraItems muestraItemsDelete = muestraItemsService.findById(id);
            muestraItemsService.delete(muestraItemsDelete);
            return new ResponseEntity<>(muestraItemsDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @GetMapping("muestra/items/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        MuestraItems muestraItems = muestraItemsService.findById(id);
        if(muestraItems==null){
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
                        .object(MuestraItemsDto.builder()
                                .id(muestraItems.getId())
                                .idMuestraMedica(muestraItems.getIdMuestraMedica())
                                .idItems(muestraItems.getIdItems())
                                .build())
                        .build()
                        ,HttpStatus.OK);
    }

    @GetMapping("muestra/items")
    public ResponseEntity<?> findAll() {
        try {
            List<MuestraItems> muestrasItems = muestraItemsService.findAll();
            List<MuestraItemsDto> muestrasItemsDto = muestrasItems.stream()
                    .map(muestraItems -> MuestraItemsDto.builder()
                            .id(muestraItems.getId())
                            .idMuestraMedica(muestraItems.getIdMuestraMedica())
                            .idItems(muestraItems.getIdItems())
                            .build())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(muestrasItemsDto, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener la lista de muestras&items: " + exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
