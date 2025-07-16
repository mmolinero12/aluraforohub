package com.alura.forohub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

/*
TopicoRespository es la interfaz con la Base de Datos
 */

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
