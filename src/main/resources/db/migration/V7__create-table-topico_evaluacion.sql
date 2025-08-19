-- Se creó esta tabla al final ya que depende de la primera tabla "usuarios" y
-- de la tabla "topicos"

CREATE TABLE topico_evaluaciones(
    id BIGINT NOT NULL AUTO_INCREMENT,
    tipo_evaluacion VARCHAR(11) NOT NULL,
    usuario_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_topicos_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topicos_topico_id FOREIGN KEY(topico_id) REFERENCES topicos(id)

);