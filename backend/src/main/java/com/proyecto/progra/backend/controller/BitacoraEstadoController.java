package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.projections.closed.IBitacoraEstadoClosedView;
import com.proyecto.progra.backend.service.IBitacoraEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class BitacoraEstadoController {
    @Autowired
    private IBitacoraEstado bitacoraEstadoService;

    @GetMapping("bitacoras/all")
    public List<IBitacoraEstadoClosedView> getBitacoraEStadoProjectionAll() {
        return bitacoraEstadoService.getAllBitacoraEstadoProjection();
    }
}
