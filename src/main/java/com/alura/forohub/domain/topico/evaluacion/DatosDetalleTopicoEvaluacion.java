package com.alura.forohub.domain.topico.evaluacion;

/**
 * Este Record proporciona los datos que serán utilizados como comprobante
 * al dar de alta un registro con like o dislike de un tópico.
 *
 * IMPORTANTE: Los nombres de las variables deben corresponder a las etiquetas
 * de los encabezados de las columnas en la tabla topicoEvaluacion y deben adoptar el
 * formato camelCase.
 *
 * @param id: PRIMARY ID de cada registro en la tabla topico_evaluacion
 * @param tipoEvaluacion: ENUM - (LIKE, DISLIKE, INDIFERENTE)
 * @param usuarioId: ID del usuario que evalúa
 * @param topicoId: ID del tópico a evaluar por el usuario
 *
 */
public record DatosDetalleTopicoEvaluacion(

        Long id,
        TipoEvaluacion tipoEvaluacion,
        Long usuarioId,
        Long topicoId

) {

    /**
     * Método Constructor - Los atributos de la Clase TopicoEvaluacion deben corresponder con las variables del Método Constructor
     * @param topicoEvaluacion
     */
    public DatosDetalleTopicoEvaluacion(TopicoEvaluacion topicoEvaluacion) {
        this(
                topicoEvaluacion.getId(),
                topicoEvaluacion.getTipoEvaluacion(),
                topicoEvaluacion.getUsuario().getId(),
                topicoEvaluacion.getTopico().getId()
        );
    }
}
