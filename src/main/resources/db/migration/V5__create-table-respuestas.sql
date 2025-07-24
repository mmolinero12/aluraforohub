create table respuestas(
    id_respuesta bigint not null auto_increment,
    mensaje varchar(250) not null,
    fecha date not null,
    id_usuario bigint not null,
    id_topico bigint not null,

    primary key(id_respuesta)

);