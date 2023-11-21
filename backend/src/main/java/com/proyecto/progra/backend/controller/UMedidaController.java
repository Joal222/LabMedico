package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.model.entity.TipoMuestra;
import com.proyecto.progra.backend.model.entity.UnidadMedida;
import com.proyecto.progra.backend.service.ITipoMuestra;
import com.proyecto.progra.backend.service.IUMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UMedidaController {

    @Autowired
    private IUMedida uMedidaService;

    @GetMapping("unidades/medida/all")
    public ResponseEntity<List<UnidadMedida>> findAll(){
        List<UnidadMedida> unidadMedidas = uMedidaService.findAll();
        return ResponseEntity.ok(unidadMedidas);
    }

    @GetMapping("unidad/medida/{id}")
    public ResponseEntity<UnidadMedida> showById(@PathVariable Integer id){
        UnidadMedida unidadMedida = uMedidaService.findById(id);
        return ResponseEntity.ok(unidadMedida);
    }
}
