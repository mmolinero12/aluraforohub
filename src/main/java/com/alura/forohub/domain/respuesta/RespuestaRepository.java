package com.alura.forohub.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;

/*
RespuestaRespository es la interfaz con la Base de Datos
 */

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}
