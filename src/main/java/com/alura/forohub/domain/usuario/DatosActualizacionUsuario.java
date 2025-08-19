package com.alura.forohub.domain.usuario;

import com.alura.forohub.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

/**
 * Esto es un DTO - Data Transfer Object para la actualización de un usuario,
 * el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 * IMPORTANTE: Los siguientes nombres de variables deben coincidir con las "Keys" del Json que se recibe
 * @param userId
 * @param activo
 * @param direccion
 * @param telefono
 * @param password
 * @param perfil
 */
public record DatosActualizacionUsuario(
        @NotNull Long userId,
        Boolean activo,
        DatosDireccion direccion,
        String telefono,
        String password,
        Perfil perfil
) {
}
