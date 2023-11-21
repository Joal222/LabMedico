package com.proyecto.progra.backend.controller;
import com.proyecto.progra.backend.model.dto.MuestraItemsDto;
import com.proyecto.progra.backend.model.entity.MuestraItems;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.IItems;
import com.proyecto.progra.backend.service.IMuestra;
import com.proyecto.progra.backend.service.IMuestraItems;
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
public class MuestraItemsController {

    @Autowired
    private IMuestraItems muestraItemsService;

    @Autowired
    private IMuestra muestraService;

    @Autowired
    private IItems itemsService;

    @DeleteMapping ("muestra-items/{id}")
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

    @GetMapping("muestras/items/all")
    public ResponseEntity<List<MuestraItems>> findAll() {
        List<MuestraItems> muestraItems = muestraItemsService.findAll();
        return ResponseEntity.ok(muestraItems);
    }
    @GetMapping("muestra/item/{id}")
    public ResponseEntity<MuestraItems> showById(@PathVariable Integer id) {
       MuestraItems muestraItems = muestraItemsService.findById(id);
        return ResponseEntity.ok(muestraItems);
    }
}
