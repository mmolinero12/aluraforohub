package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.topico.Curso;
import com.alura.forohub.domain.topico.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroRespuesta(
        // Los siguientes nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotBlank String mensaje,
        @NotNull
        @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
        String fecha,
        @NotNull Long idUsuario,
        @NotNull Long idTopico
) {
}
