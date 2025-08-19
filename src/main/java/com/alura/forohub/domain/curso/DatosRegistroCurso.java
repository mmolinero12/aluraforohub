package com.alura.forohub.domain.curso;

/*
Esto es un DTO - Data Transfer Object para el registro de un nuevo usuario,
el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 */

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        // Los siguiente nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotBlank(message = "Nombre del Curso es obligatorio") String nombre,
        //@NotBlank (message = "Aula es obligatoria") String aula,
        //@NotBlank (message = "Clase es obligatoria") String clase,
        Categoria categoria

) {

}
