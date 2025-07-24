package com.alura.forohub.domain.topico;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public record DatosDetalleTopico(
        Long id_topico,
        String titulo,
        String mensaje,
        LocalDate fecha,
        Status status,
        Long id_usuario,
        Curso curso
) {
    public DatosDetalleTopico(Topico topico){
        this(
          topico.getId_topico(),
          topico.getTitulo(),
          topico.getMensaje(),
          topico.getFecha(),
          topico.getStatus(),
          topico.getId_usuario(),
          topico.getCurso()
        );
    }
}
