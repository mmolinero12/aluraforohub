package com.alura.forohub.domain.curso;


import com.alura.forohub.domain.respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
CursoRespository es la interfaz con la Base de Datos
 */

public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("""
            select count(c) > 0 from Curso c
            where
            c.nombre = :nombre
            """
    )
    boolean existsByNombreCurso(String nombre);
}
