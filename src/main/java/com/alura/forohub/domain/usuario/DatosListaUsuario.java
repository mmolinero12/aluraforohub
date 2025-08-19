package com.alura.forohub.domain.usuario;

import com.alura.forohub.domain.direccion.Direccion;

import java.time.LocalDate;


/**
 * Este Record proporciona los datos que serán solicitados por los GET Listar Usuarios y
 * Listar Usuario. Se omite password por obvias razones.
 *
 * IMPORTANTE: Los nombres de las variables corresponden a las etiquetas
 * de los encabezados de las columnas en la tabla usuarios adoptando el
 * formato camelCase.
 *
 * @param userId
 * @param activo
 * @param nombre
 * @param apellidoPaterno
 * @param apellidoMaterno
 * @param fechaNacimiento
 * @param genero
 * @param direccion
 * @param email
 * @param telefono
 * @param username
 * @param perfil
 */
public record DatosListaUsuario(
        Long userId,
        Boolean activo,
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        LocalDate fechaNacimiento,
        String genero,
        Direccion direccion,
        String email,
        String telefono,
        String username,
        Perfil perfil

) {
    /**
     * Método Constructor del Record
     * @param usuario
     */
    public DatosListaUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getActivo(),
                usuario.getNombre(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getFechaNacimiento(),
                usuario.getGenero(),
                usuario.getDireccion(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getUsername(),
                usuario.getPerfil()

        );
    }
}
