package com.alura.forohub.domain.respuesta.evaluacion;

import com.alura.forohub.domain.topico.evaluacion.TipoEvaluacion;
import jakarta.validation.constraints.NotNull;

/**
 * Esto es un DTO - Data Transfer Object para la actualización de likes y dislikes de un tópico,
 * el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 * IMPORTANTE: Los siguientes nombres de variables deben coincidir con las "Keys" del Json que se recibe
 * @param tipoEvaluacion
 * @param respuestaId
 * @param usuarioId
 */
public record DatosActualizacionRespuestaEvaluacion(
        @NotNull TipoEvaluacion tipoEvaluacion,
        @NotNull Long respuestaId,
        @NotNull Long usuarioId

        ) {

}
