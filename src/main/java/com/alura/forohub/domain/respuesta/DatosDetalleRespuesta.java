package com.alura.forohub.domain.respuesta;


import java.time.LocalDate;

public record DatosDetalleRespuesta(
        Long id_respuesta,
        LocalDate fecha,
        String mensaje,
        Long id_usuario,
        Long id_topico
) {
    public DatosDetalleRespuesta(Respuesta respuesta){
        this(
                respuesta.getId_respuesta(),
                respuesta.getFecha(),
                respuesta.getMensaje(),
                respuesta.getId_usuario(),
                respuesta.getId_topico()
        );
    }

}
