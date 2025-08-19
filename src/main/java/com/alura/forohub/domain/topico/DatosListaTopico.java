package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Categoria;

import java.time.LocalDate;

/**
 * Este Record proporciona los datos que serán solicitados por los GET Listar Topicos y
 * Listar Topico.
 *
 * IMPORTANTE: Los nombres de las variables corresponden a las etiquetas
 * de los encabezados de las columnas en la tabla topicos adoptando el
 * formato camelCase.
 *
 * @param topicId
 * @param titulo
 * @param mensaje
 * @param status
 * @param username
 * @param cursoName
 * @param categoria
 * @param fechaCreacion
 * @param fechaUltimaActualizacion
 */
public record DatosListaTopico(
        Long topicId,
        String titulo,
        String mensaje,
        Status status,
        String username,
        String cursoName,
        Categoria categoria,
        LocalDate fechaCreacion,
        LocalDate fechaUltimaActualizacion
) {

    /**
     * Método Constructor del Record
     * @param topico
     */
    public DatosListaTopico(Topico topico) {

        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getUsuario().getUsername(),
                topico.getCurso().getNombre(),
                topico.getCurso().getCategoria(),
                topico.getFechaCreacion(),
                topico.getFechaUltimaActualizacion()
        );
    }
}
