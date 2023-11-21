package com.proyecto.progra.backend.controller;
import com.proyecto.progra.backend.model.entity.TipoMuestra;
import com.proyecto.progra.backend.service.ITipoMuestra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TipoMuestraController {

    @Autowired
    private ITipoMuestra tipoMuestraService;

    @GetMapping("tipos/muestras/all")
    public ResponseEntity<List<TipoMuestra>> findAll() {
        List<TipoMuestra> tipoMuestras = tipoMuestraService.findAll();
        return ResponseEntity.ok(tipoMuestras);
    }

    @GetMapping("tipo/muestra/{id}")
    public ResponseEntity<TipoMuestra> showById(@PathVariable Integer id) {
        TipoMuestra tipoMuestra = tipoMuestraService.findById(id);
        return ResponseEntity.ok(tipoMuestra);
    }
}
