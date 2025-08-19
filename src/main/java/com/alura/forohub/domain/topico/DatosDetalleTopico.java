package com.alura.forohub.domain.topico;

import java.time.LocalDate;

/**
 * Este Record proporciona los datos que serán utilizados como comprobante
 * al dar de alta un tópico.
 *
 * IMPORTANTE: Los nombres de las variables deben corresponder a las etiquetas
 * de los encabezados de las columnas en la tabla topicos y deben adoptar el
 * formato camelCase.
 *
 * @param id
 * @param titulo
 * @param mensaje
 * @param status
 * @param username
 * @param nombreCurso
 * @param fechaCreacion
 * @param fechaUltimaActualizacion
 *
 */
public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        Status status,
        String username,
        String nombreCurso,
        LocalDate fechaCreacion,
        LocalDate fechaUltimaActualizacion

) {


    /**
     * Método Constructor - Los atributos de la Clase Topico deben corresponder con las variables del Método Constructor
     * @param topico
     */
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getUsuario().getUsername(),
                topico.getCurso().getNombre(),
                topico.getFechaCreacion(),
                topico.getFechaUltimaActualizacion()
        );
    }
}
