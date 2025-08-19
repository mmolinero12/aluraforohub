-- Se creó esta segunda tabla ya que depende de la primera tabla "usuarios"

-- Se prefirió el campo <datetime> ya que la creación de un tópico y
-- una respuesta se pueden dar el mismo día.

-- Se requieren dos campos de fecha, uno para la fecha de creación del tópico
-- y una fecha de la última actualización del tópico por parte del usuario que la creó.

CREATE TABLE topicos(
    id bigint NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL unique,
    mensaje VARCHAR(250) NOT NULL,
    fecha_creacion DATE NOT NULL,
    fecha_ultima_actualizacion DATE NOT NULL,
    status VARCHAR(10) NOT NULL,
    creador_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_topicos_creador_id FOREIGN KEY(creador_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topicos_curso_id FOREIGN KEY(curso_id) REFERENCES cursos(id)
);