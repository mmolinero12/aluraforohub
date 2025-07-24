package com.alura.forohub.domain.usuario;

import com.alura.forohub.domain.direccion.Direccion;

import java.time.LocalDate;

public record DatosListaFullUsuario(
        Long id_usuario,
        Boolean activo,
        String nombre,
        String apellido_paterno,
        String apellido_materno,
        LocalDate fecha_nacimiento,
        String genero,
        Direccion direccion,
        String email,
        String telefono,
        String username,
        String password,
        Perfil perfil

) {
    public DatosListaFullUsuario(Usuario usuario) {
        this(
                usuario.getId_usuario(),
                usuario.getActivo(),
                usuario.getNombre(),
                usuario.getApellido_paterno(),
                usuario.getApellido_materno(),
                usuario.getFecha_nacimiento(),
                usuario.getGenero(),
                usuario.getDireccion(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getPerfil()

        );
    }
}
