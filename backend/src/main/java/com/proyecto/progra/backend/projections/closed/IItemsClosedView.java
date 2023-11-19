package com.proyecto.progra.backend.projections.closed;

import jakarta.persistence.criteria.CriteriaBuilder;

public interface IItemsClosedView {
    Integer getId();
    Integer getIdSolicitudMuestraMedica();
    Integer getIdTipoItems();
}
