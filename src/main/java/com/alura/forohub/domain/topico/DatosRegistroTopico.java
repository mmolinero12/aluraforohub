package com.alura.forohub.domain.topico;

/*
Esto es un DTO - Data Transfer Object, el cual permite encapsular los datos que recibimos y enviamos
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DatosRegistroTopico(
        // Los siguientes nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull
        @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
        String fecha,
        @NotNull Status status,
        @NotNull Long idUsuario,
        @NotNull Curso curso
) {

}
