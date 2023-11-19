package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.projections.closed.IBitacoraEstadoClosedView;

import java.util.List;

public interface IBitacoraEstado {
    List<IBitacoraEstadoClosedView> getAllBitacoraEstadoProjection();
}
