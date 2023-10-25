package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.service.ISolicitud;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class SolicitudController {

    private ISolicitud solicitudService;

    public SolicitudController(ISolicitud solicitudService){
        this.solicitudService =solicitudService;
    }
    /*
    ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity
    */
}
