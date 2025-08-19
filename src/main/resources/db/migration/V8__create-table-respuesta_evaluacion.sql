-- Se creó esta tabla al final ya que depende de la primera tabla "usuarios" y
-- de la tabla "respuestas"

CREATE TABLE respuesta_evaluaciones(
    id BIGINT NOT NULL AUTO_INCREMENT,
    tipo_evaluacion VARCHAR(11) NOT NULL,
    usuario_id BIGINT NOT NULL,
    respuesta_id BIGINT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_respuestas_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_respuestas_respuesta_id FOREIGN KEY(respuesta_id) REFERENCES respuestas(id)

);