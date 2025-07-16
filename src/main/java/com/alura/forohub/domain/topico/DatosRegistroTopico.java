package com.alura.forohub.domain.topico;

/*
Esto es un DTO - Data Transfer Object, el cual permite encapsular los datos que recibimos y enviamos
 */

import java.time.LocalDate;

public record DatosRegistroTopico(
        // Los siguientes nombres de variables deben coincidir con las "Keys" en el Json que se recibe
        String titulo,
        String mensaje,
        LocalDate fecha,
        Status status,
        Long idUsuario,
        Curso curso
) {

}
