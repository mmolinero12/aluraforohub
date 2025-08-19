package com.alura.forohub.domain.respuesta.evaluacion;

import com.alura.forohub.domain.topico.evaluacion.TipoEvaluacion;
import jakarta.validation.constraints.NotNull;

/*
Esto es un DTO - Data Transfer Object para el registro de un nuevo like o dislike para alguna respuesta,
el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 */

public record DatosRegistroRespuestaEvaluacion(
        // Los siguientes nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotNull TipoEvaluacion tipoEvaluacion,
        @NotNull (message = "Id de usuario es obligatorio") Long usuarioId,
        @NotNull (message = "Id de respuesta es obligatorio") Long respuestaId
) {

}
