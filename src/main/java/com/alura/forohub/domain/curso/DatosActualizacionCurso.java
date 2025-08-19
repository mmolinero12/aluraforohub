package com.alura.forohub.domain.curso;

import jakarta.validation.constraints.NotNull;

/**
 * Esto es un DTO - Data Transfer Object para la actualización de un curso,
 * el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 * IMPORTANTE: Los siguientes nombres de variables deben coincidir con las "Keys" del Json que se recibe
 * @param courseId
 * @param nombre
 */
public record DatosActualizacionCurso(
        @NotNull Long courseId,
        String nombre
) {
}
