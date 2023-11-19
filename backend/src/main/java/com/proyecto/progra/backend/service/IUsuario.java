package com.proyecto.progra.backend.service;
import com.proyecto.progra.backend.model.entity.Usuario;
import com.proyecto.progra.backend.projections.closed.IUsuarioClosedView;

import java.util.List;

public interface IUsuario {
    Usuario save(Usuario usuario);
    Usuario findByCui(String cui);
    Usuario findById(Integer id);
    void delete(Usuario usuario);
    List<Usuario> findAll();
    boolean existById(Integer id);
    List<IUsuarioClosedView> getAllUsuarioProjection();
}
