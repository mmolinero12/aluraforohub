package com.alura.forohub.domain.topico.evaluacion;

import jakarta.validation.constraints.NotNull;

/*
Esto es un DTO - Data Transfer Object para el registro de un nuevo topico,
el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 */

public record DatosRegistroTopicoEvaluacion(
        // Los siguientes nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotNull TipoEvaluacion tipoEvaluacion,
        @NotNull (message = "Id de usuario es obligatorio") Long usuarioId,
        @NotNull (message = "Id de tópico es obligatorio") Long topicoId
) {

}
