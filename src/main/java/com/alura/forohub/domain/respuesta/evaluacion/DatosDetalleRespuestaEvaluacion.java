package com.alura.forohub.domain.respuesta.evaluacion;

import com.alura.forohub.domain.topico.evaluacion.TipoEvaluacion;

/**
 * Este Record proporciona los datos que serán utilizados como comprobante
 * al dar de alta un registro con like o dislike de una respuesta.
 *
 * IMPORTANTE: Los nombres de las variables deben corresponder a las etiquetas
 * de los encabezados de las columnas en la tabla respuestaEvaluacion y deben adoptar el
 * formato camelCase.
 *
 * @param id: PRIMARY ID de cada registro en la tabla respuesta_evaluacion
 * @param tipoEvaluacion: ENUM - (LIKE, DISLIKE, INDIFERENTE)
 * @param usuarioId: ID del usuario que evalúa
 * @param respuestaId: ID de la respuesta a evaluar por el usuario
 *
 */
public record DatosDetalleRespuestaEvaluacion(

        Long id,
        TipoEvaluacion tipoEvaluacion,
        Long usuarioId,
        Long respuestaId

) {

    /**
     * Método Constructor - Los atributos de la Clase RespuestaEvaluacion deben corresponder con las variables del Método Constructor
     * @param respuestaEvaluacion
     */
    public DatosDetalleRespuestaEvaluacion(RespuestaEvaluacion respuestaEvaluacion) {
        this(
                respuestaEvaluacion.getId(),
                respuestaEvaluacion.getTipoEvaluacion(),
                respuestaEvaluacion.getUsuario().getId(),
                respuestaEvaluacion.getRespuesta().getId()
        );
    }
}
