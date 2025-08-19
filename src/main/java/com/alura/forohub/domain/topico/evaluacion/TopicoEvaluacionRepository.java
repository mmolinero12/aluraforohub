package com.alura.forohub.domain.topico.evaluacion;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

/*
TopicoEvaluacionRespository es la interfaz con la Base de Datos
 */

public interface TopicoEvaluacionRepository extends JpaRepository<TopicoEvaluacion, Long> {

    // Recordar que en la tabla los nombres de los campos son usuario_id y topico_id. Al
    // escribirlos en JPA el guión bajo "_" se convierte en punto ".".
    @Query("""
            select count(e) > 0 from TopicoEvaluacion e
            where
            e.usuario.id = :userId
            and
            e.topico.id = :topicId
            """
    )
    boolean findTopicoIdEvaluacion(@NotNull Long userId, @NotNull Long topicId);

    @Query("""
            select e from TopicoEvaluacion e
            where
            e.usuario.id = :userId
            and
            e.topico.id = :topicId
            """
    )
    TopicoEvaluacion findTopicoEvaluacion(@NotNull Long userId, @NotNull Long topicId);

    @Query("""
            select count(e) > 0 from TopicoEvaluacion e
            where
            e.usuario.id = :usuarioId and e.topico.id = :topicoId
            """
    )
    boolean existsByUsuarioIdAndTopicoId(@NotNull(message = "Id de usuario es obligatorio") Long usuarioId, @NotNull(message = "Id de tópico es obligatorio") Long topicoId);


}
