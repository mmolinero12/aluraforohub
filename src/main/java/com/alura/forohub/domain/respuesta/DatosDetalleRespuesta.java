package com.alura.forohub.domain.respuesta;

import java.time.LocalDate;

/**
 * Este Record proporciona los datos que serán utilizados como comprobante
 * al dar de alta una respuesta.
 *
 * IMPORTANTE: Los nombres de las variables deben corresponder a las etiquetas
 * de los encabezados de las columnas en la tabla topicos y deben adoptar el
 * formato camelCase.
 *
 * @param id
 * @param mensaje
 * @param username
 * @param topicName
 * @param fechaCreacion
 * @param fechaUltimaActualizacion
 * @param solucion
 */
public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        String username,
        String topicName,
        LocalDate fechaCreacion,
        LocalDate fechaUltimaActualizacion,
        Boolean solucion
) {
    /**
     * Método Constructor - Los atributos de la Clase Respuesta deben corresponder con las variables del Método Constructor
     * @param respuesta
     */
    public DatosDetalleRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getUsuario().getUsername(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFechaCreacion(),
                respuesta.getFechaUltimaActualizacion(),
                respuesta.getSolucion()
        );
    }

}
