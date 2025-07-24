package com.alura.forohub.domain.topico;

public record DatosListaTopico(
        Long id_topico,
        String titulo,
        String mensaje,
        String fecha,
        Status status,
        Long id_usuario,
        Curso curso
) {
    public DatosListaTopico(Topico topico) {

        this(
                topico.getId_topico(),
                topico.getTitulo(),
                topico.getMensaje(),
                String.valueOf(topico.getFecha()),
                topico.getStatus(),
                topico.getId_usuario(),
                topico.getCurso()
        );
    }
}
