package com.alura.forohub.domain.respuesta.evaluacion;

import com.alura.forohub.domain.respuesta.evaluacion.RespuestaEvaluacion;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
RespuestaEvaluacionRespository es la interfaz con la Base de Datos
 */

public interface RespuestaEvaluacionRepository extends JpaRepository<RespuestaEvaluacion, Long> {

    // Recordar que en la tabla los nombres de los campos son usuario_id y respuesta_id. Al
    // escribirlos en JPA el guión bajo "_" se convierte en punto ".".
    @Query("""
            select count(e) > 0 from RespuestaEvaluacion e
            where
            e.usuario.id = :userId
            and
            e.respuesta.id = :answerId
            """
    )
    boolean findRespuestaIdEvaluacion(@NotNull Long userId, @NotNull Long answerId);

    @Query("""
            select e from RespuestaEvaluacion e
            where
            e.usuario.id = :userId
            and
            e.respuesta.id = :answerId
            """
    )
    RespuestaEvaluacion findRespuestaEvaluacion(@NotNull Long userId, @NotNull Long answerId);

    @Query("""
            select count(e) > 0 from RespuestaEvaluacion e
            where
            e.usuario.id = :userId and e.respuesta.id = :answerId
            """
    )
    boolean existsByUsuarioIdAndRespuestaId(@NotNull(message = "Id de usuario es obligatorio") Long userId, @NotNull(message = "Id de respuesta es obligatorio") Long answerId);

}
