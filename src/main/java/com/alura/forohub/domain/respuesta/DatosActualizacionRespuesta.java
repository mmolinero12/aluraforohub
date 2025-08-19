package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.topico.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


/**
 * Esto es un DTO - Data Transfer Object para la actualización de una respuesta,
 * el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 * IMPORTANTE: Los siguientes nombres de variables deben coincidir con las "Keys" del Json que se recibe
 * @param answerId
 * @param mensaje
 */
public record DatosActualizacionRespuesta(
        @NotNull Long answerId,
        String mensaje
) {
}
