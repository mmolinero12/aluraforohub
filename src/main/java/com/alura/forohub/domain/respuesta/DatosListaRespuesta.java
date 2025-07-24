package com.alura.forohub.domain.respuesta;

public record DatosListaRespuesta(
        Long id_respuesta,
        String mensaje,
        String fecha,
        Long id_usuario,
        Long id_topico
) {
    public DatosListaRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId_respuesta(),
                respuesta.getMensaje(),
                String.valueOf(respuesta.getFecha()),
                respuesta.getId_usuario(),
                respuesta.getId_topico()
        );
    }
}
