package com.alura.forohub.domain.usuario;

import com.alura.forohub.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionUsuario(
        @NotNull Long id_usuario,
        Boolean activo,
        DatosDireccion direccion,
        String telefono,
        String password,
        Perfil perfil
) {
}
