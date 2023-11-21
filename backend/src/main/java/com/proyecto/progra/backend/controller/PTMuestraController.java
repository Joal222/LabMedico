package com.proyecto.progra.backend.controller;
import com.proyecto.progra.backend.model.entity.PresentacionMuestra;
import com.proyecto.progra.backend.service.IPTMuestra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class PTMuestraController {

    @Autowired
    private IPTMuestra pTMuestraService;

    @GetMapping("ptmuestras/all")
    public ResponseEntity<List<PresentacionMuestra>> findAll() {
        List<PresentacionMuestra> presentacionMuestras = pTMuestraService.findAll();
        return ResponseEntity.ok(presentacionMuestras);
    }

    @GetMapping("ptmuestra/{id}")
    public ResponseEntity<PresentacionMuestra> showById(@PathVariable Integer id) {
        PresentacionMuestra presentacionMuestra = pTMuestraService.findById(id);
        return ResponseEntity.ok(presentacionMuestra);
    }
}
