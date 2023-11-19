package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.projections.closed.ITipoEstadoClosedView;
import com.proyecto.progra.backend.service.ITipoEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TipoEstadoController {

    @Autowired
    private ITipoEstado tipoEstadoService;

    @GetMapping("tipo-estado/all")
    public List<ITipoEstadoClosedView> getTipoEstadoAll(){
        return tipoEstadoService.getAllTipoEstadoProjection();
    }

}
