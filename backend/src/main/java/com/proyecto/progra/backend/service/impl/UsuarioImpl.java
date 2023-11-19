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
    /*
    @Transactional
    @Override
    public Usuario save(UsuarioDto usuariodto) {
        Usuario usuario = Usuario.builder()
                .id(usuariodto.getId())
                .cui(usuariodto.getCui())
                .idTipoUsuario(usuariodto.getIdTipoUsuario())
                .nit(usuariodto.getNit())
                .nombres(usuariodto.getNombres())
                .apellidos(usuariodto.getApellidos())
                .email(usuariodto.getEmail())
                .genero(usuariodto.getGenero())
                .telefono(usuariodto.getTelefono())
                .direccion(usuariodto.getDireccion())
                .password(usuariodto.getPassword())
                .build();

        return usuarioDao.save(usuario);
    }
     */
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

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

    @Override
    public boolean existById(Integer id) {

        return usuarioDao.existsById(id);
    }

    @Override
    public List<IUsuarioClosedView> getAllUsuarioProjection() {

        return usuarioDao.findAllProjectedBy();
    }
}
