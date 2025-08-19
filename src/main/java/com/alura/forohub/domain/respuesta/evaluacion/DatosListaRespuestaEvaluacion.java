package com.alura.forohub.domain.respuesta.evaluacion;

import com.alura.forohub.domain.topico.evaluacion.TipoEvaluacion;

/**
 * Este Record proporciona los datos que serán solicitados por los GET Listar RespuestaEvaluacion y
 * Listar RespuestaEvaluacion.
 *
 * IMPORTANTE: Los nombres de las variables corresponden a las etiquetas
 * de los encabezados de las columnas en la tabla respuesta_evaluacion adoptando el
 * formato camelCase.
 *
 * @param respuestaEvaluacionId
 * @param tipoEvaluacion
 * @param username
 * @param mensajeName
 */
public record DatosListaRespuestaEvaluacion(
        Long respuestaEvaluacionId,
        TipoEvaluacion tipoEvaluacion,
        String username,
        String mensajeName
) {

    /**
     * Método Constructor del Record
     * @param respuestaEvaluacion
     */
    public DatosListaRespuestaEvaluacion(RespuestaEvaluacion respuestaEvaluacion) {

        this(
                respuestaEvaluacion.getId(),
                respuestaEvaluacion.getTipoEvaluacion(),
                respuestaEvaluacion.getUsuario().getUsername(),
                respuestaEvaluacion.getRespuesta().getMensaje()

        );
    }

}
