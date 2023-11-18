package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.projections.estado.IBitacoraClosedView;

import java.util.List;

public interface IBitacora {
    List<IBitacoraClosedView> getBitacoraProjectionAll();
}
