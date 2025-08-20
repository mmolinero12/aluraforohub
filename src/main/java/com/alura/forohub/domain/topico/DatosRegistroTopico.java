package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

/*
Esto es un DTO - Data Transfer Object para el registro de un nuevo topico,
el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 */

public record DatosRegistroTopico(
        // Los siguientes nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotBlank (message = "Título es obligatorio") String titulo,
        @NotBlank (message = "Mensaje es obligatorio") String mensaje,
        @NotNull (message = "Curso es obligatorio") Long cursoId

) {

}
