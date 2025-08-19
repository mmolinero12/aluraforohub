package com.alura.forohub.domain.curso;

import com.alura.forohub.domain.usuario.Usuario;

/**
 * Este Record proporciona los datos que serán utilizados como comprobante
 * al dar de alta un nuevo curso .
 *
 * IMPORTANTE: Los nombres de las variables deben corresponder a las etiquetas
 * de los encabezados de las columnas en la tabla topicos y deben adoptar el
 * formato camelCase. El Primary Id puede incluir un prefijo descriptivo "course"
 *
 * @param courseId
 * @param nombre
 * @param categoria
 */
public record DatosDetalleCurso(
        Long courseId,
        String nombre,
        String categoria
) {
    /**
     * Método Constructor del Record
     * @param curso
     */
    public DatosDetalleCurso(Curso curso){

        this(
                curso.getId(),
                curso.getNombre(),
                // Validación que valor de una variable esté en un ENUM
                String.valueOf(curso.getCategoria())
        );
    }

}
