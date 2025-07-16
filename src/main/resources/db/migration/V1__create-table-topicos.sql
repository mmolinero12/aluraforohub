create table topicos(
    id_topico bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(250) not null,
    fecha date not null,
    status varchar(10) not null,
    id_usuario bigint not null,
    curso varchar(100) not null,

    primary key(id_topico)

);