package com.alura.forohub.domain.topico.evaluacion;

import jakarta.validation.constraints.NotNull;

/**
 * Esto es un DTO - Data Transfer Object para la actualización de likes y dislikes de un tópico,
 * el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 * IMPORTANTE: Los siguientes nombres de variables deben coincidir con las "Keys" del Json que se recibe
 * @param tipoEvaluacion
 * @param topicoId
 * @param usuarioId
 */
public record DatosActualizacionTopicoEvaluacion(
        @NotNull TipoEvaluacion tipoEvaluacion,
        @NotNull Long topicoId,
        @NotNull Long usuarioId

) {

}
