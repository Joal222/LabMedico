package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.projections.tipoSolicitud.ITipoSolicitudClosedView;
import com.proyecto.progra.backend.service.ITipoSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class TipoSolicitudController {

    @Autowired
    private ITipoSolicitud tipoSolicitudService;

    @GetMapping("tipo-solicitud/all")
    public List<ITipoSolicitudClosedView> getTipoSolicitudAll(){
        return tipoSolicitudService.getAllTiposSolicitudProjection();
    }
}
