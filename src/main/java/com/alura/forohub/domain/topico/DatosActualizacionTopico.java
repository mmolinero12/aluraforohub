package com.alura.forohub.domain.topico;

/*
Esto es un DTO - Data Transfer Object, el cual permite encapsular los datos que recibimos y enviamos
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosActualizacionTopico(
        // Los siguientes nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotNull Long id_topico,
        String titulo,
        String mensaje,
        Status status
) {

}
