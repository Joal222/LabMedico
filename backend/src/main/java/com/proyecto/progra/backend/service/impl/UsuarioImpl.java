package com.proyecto.progra.backend.service.impl;
import com.proyecto.progra.backend.model.dao.UsuarioDao;
import com.proyecto.progra.backend.model.entity.Usuario;
import com.proyecto.progra.backend.projections.closed.IUsuarioClosedView;
import com.proyecto.progra.backend.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UsuarioImpl implements IUsuario {
    @Autowired
    private UsuarioDao usuarioDao;
    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Transactional
    @Override
    public Usuario update(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Transactional
    @Override
    public Usuario findByCui(String cui) {
        return usuarioDao.findByCui(cui);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario findById(Integer id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Usuario usuario) {

        usuarioDao.delete(usuario);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {

        return (List<Usuario>) usuarioDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existById(Integer id) {

        return usuarioDao.existsById(id);
    }

    @Transactional
    @Override
    public List<IUsuarioClosedView> getAllUsuarioProjection() {

        return usuarioDao.findAllProjectedBy();
    }
}
