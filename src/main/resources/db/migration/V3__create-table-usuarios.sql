create table usuarios(
    id_usuario bigint not null auto_increment,
    nombre varchar(100) not null,
    apellido_paterno varchar(25) not null,
    apellido_materno varchar(25) not null,

    fecha_nacimiento date not null,
    genero varchar(25) not null,

    calle varchar(100) not null,
    numero varchar(20),
    colonia varchar(100) not null,
    codigo_postal varchar(12) not null,
    ciudad varchar(100) not null,
    estado varchar(100) not null,

    email varchar(100) not null unique,
    telefono varchar(20) not null,

    username varchar(15) not null,
    password varchar(255) not null,
    perfil  varchar(100) not null,

    primary key(id_usuario)

);