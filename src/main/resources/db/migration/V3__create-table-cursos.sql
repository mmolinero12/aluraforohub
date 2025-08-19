-- Se creó esta segunda tabla ya que NO depende directamente de alguna otra
-- Snake Case: Esta es la convención más utilizada y recomendada, especialmente en bases de datos.
-- Consiste en escribir *** todo en minúsculas y separar las palabras con un guion bajo ***

-- Ventajas: Es fácil de leer, compatible con SQL estándar y evita problemas con la
-- distinción entre mayúsculas y minúsculas que puede variar entre diferentes sistemas operativos o configuraciones.

CREATE TABLE cursos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    categoria  VARCHAR(50) NOT NULL,

    PRIMARY KEY(id)

);