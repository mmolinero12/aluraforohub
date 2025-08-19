package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotNull;

/**
 * Esto es un DTO - Data Transfer Object para la actualización de un tópico,
 * el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 * IMPORTANTE: Los siguientes nombres de variables deben coincidir con las "Keys" del Json que se recibe
 * @param topicId
 * @param titulo
 * @param mensaje
 * @param status
 */
public record DatosActualizacionTopico(
        @NotNull Long topicId,
        String titulo,
        String mensaje,
        Status status
) {

}
