package com.alura.forohub.domain.topico.evaluacion;

/**
 * Este Record proporciona los datos que serán solicitados por los GET Listar TopicoEvaluacion y
 * Listar TopicoEvaluacion.
 *
 * IMPORTANTE: Los nombres de las variables corresponden a las etiquetas
 * de los encabezados de las columnas en la tabla topico_evaluacion adoptando el
 * formato camelCase.
 *
 * @param topicEvaluacionId
 * @param tipoEvaluacion
 * @param username
 * @param topicName
 */
public record DatosListaTopicoEvaluacion(
        Long topicEvaluacionId,
        TipoEvaluacion tipoEvaluacion,
        String username,
        String topicName
) {

    /**
     * Método Constructor del Record
     * @param topicoEvaluacion
     */
    public DatosListaTopicoEvaluacion(TopicoEvaluacion topicoEvaluacion) {

        this(
                topicoEvaluacion.getId(),
                topicoEvaluacion.getTipoEvaluacion(),
                topicoEvaluacion.getUsuario().getUsername(),
                topicoEvaluacion.getTopico().getTitulo()

        );
    }

}
