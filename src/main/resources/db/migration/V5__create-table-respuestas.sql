-- Se creó esta tabla casi al final ya que depende de la primera tabla "usuarios" y
-- de la segunda "topicos"

-- Se prefirió el campo <datetime> ya que la creación de un tópico y
-- una respuesta se pueden dar el mismo día

-- Se requieren dos campos de fecha, uno para la fecha de creación de la respuesta
-- y una fecha de la última actualización de la respuesta por parte del usuario que la creó.

CREATE TABLE respuestas(
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(250) NOT NULL,
    fecha_creacion DATE NOT NULL,
    fecha_ultima_actualizacion DATE NOT NULL,
    creador_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_respuestas_creador_id FOREIGN KEY(creador_id) REFERENCES usuarios(id),
    CONSTRAINT fk_respuestas_topico_id FOREIGN KEY(topico_id) REFERENCES topicos(id)

);