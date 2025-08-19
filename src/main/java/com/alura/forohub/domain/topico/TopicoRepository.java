package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

/*
TopicoRespository es la interfaz con la Base de Datos
 */

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("""
            select count(t) > 0 from Topico t
            where
            t.fechaCreacion = :fecha
            """
    )
    boolean existsByFechaCreacion(LocalDate fecha);

    Page<Topico> findAll(Pageable paginacion);

    Page<Topico> findByUsuarioUsername(String username, Pageable paginacion);
    Page<Topico> findByCursoNombre(String nombre, Pageable paginacion);
    Page<Topico> findByCursoCategoria(Categoria categoria, Pageable paginacion);
    Page<Topico> findByFechaCreacion(LocalDate fechaCreacion, Pageable paginacion);

}
