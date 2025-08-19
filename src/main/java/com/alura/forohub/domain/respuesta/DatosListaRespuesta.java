package com.alura.forohub.domain.respuesta;

import java.time.LocalDate;

/**
 * Este Record proporciona los datos que serán solicitados por los GET Listar Respuestas y
 * Listar Respuesta.
 *
 * IMPORTANTE: Los nombres de las variables corresponden a las etiquetas
 * de los encabezados de las columnas en la tabla respuestas adoptando el
 * formato camelCase.
 *
 * @param answerId
 * @param mensaje
 * @param username
 * @param topicName
 * @param fechaCreacion
 * @param fechaUltimaActualizacion
 */
public record DatosListaRespuesta(
        Long answerId,
        String mensaje,
        String username,
        String topicName,
        LocalDate fechaCreacion,
        LocalDate fechaUltimaActualizacion
) {

    /**
     * Método Constructor del Record
     * @param respuesta
     */
    public DatosListaRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getUsuario().getUsername(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFechaCreacion(),
                respuesta.getFechaUltimaActualizacion()
        );
    }
}
