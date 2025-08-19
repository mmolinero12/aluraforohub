-- Se creó esta primera tabla ya que NO depende directamente de alguna otra
-- Snake Case: Esta es la convención más utilizada y recomendada, especialmente en bases de datos.
-- Consiste en escribir *** todo en minúsculas y separar las palabras con un guion bajo ***

-- Ventajas: Es fácil de leer, compatible con SQL estándar y evita problemas con la
-- distinción entre mayúsculas y minúsculas que puede variar entre diferentes sistemas operativos o configuraciones.

CREATE TABLE usuarios(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(25) NOT NULL,
    apellido_materno VARCHAR(25) NOT NULL,

    fecha_nacimiento date NOT NULL,
    genero VARCHAR(25) NOT NULL,

    calle VARCHAR(100) NOT NULL,
    numero VARCHAR(20),
    colonia VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(12) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL,

    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,

    username VARCHAR(15) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    perfil  varchar(100) NOT NULL,

    PRIMARY KEY(id)

);