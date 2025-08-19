package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Categoria;

import java.time.LocalDate;

/**
 * Esto es un DTO - Data Transfer Object para conocer cómo será la ordenación de una lista de un tópicos,
 * el cual permite encapsular los datos que se reciben desde el navegador o la aplicación cliente.
 * IMPORTANTE: Los siguientes nombres de variables deben coincidir con las "Keys" del Json que se recibe
 * @param criteriosDeBusqueda
 * @param ordenamiento
 */
public record DatosOrdenacionTopico(

        CriteriosDeBusqueda criteriosDeBusqueda,
        Ordenamiento ordenamiento
) {

// Records anidados. A petición de Gemini, los Records deben ser estáticos para evitar
// problemas de visibilidad
public static record CriteriosDeBusqueda(String username, String nombreCurso, LocalDate fechaCreacion, Categoria categoria ){}

public static record Ordenamiento(String campo, String direccionOrdenacion){}

}
