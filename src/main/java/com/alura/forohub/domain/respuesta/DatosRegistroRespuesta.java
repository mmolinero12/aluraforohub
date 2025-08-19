package com.alura.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
Esto es un DTO - Data Transfer Object para el registro de una nueva respuesta
el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 */
public record DatosRegistroRespuesta(
        // Los siguientes nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotBlank (message = "Mensaje es obligatorio") String mensaje,
        @NotNull (message = "Id de usuario es obligatorio") Long creadorId,
        @NotNull (message = "Id de topico es obligatorio") Long topicId

) {
}
