package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.topico.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosActualizacionRespuesta(
        // Los siguientes nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotNull Long id_respuesta,
        String mensaje,
        @NotNull
        @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
        String fecha
) {
}
