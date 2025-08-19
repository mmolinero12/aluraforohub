package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.ValidacionException;
import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistroDeRespuestas {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository creadorRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosDetalleRespuesta registrar(DatosRegistroRespuesta datos){
        if(!creadorRepository.existsById(datos.creadorId())){
            throw new ValidacionException("No existe un usuario con el id proporcionado");
        }

        if(!topicoRepository.existsById(datos.topicId())){
            throw new ValidacionException("No existe un tópico con el id proporcionado");
        }

        var creador = creadorRepository.findById(datos.creadorId()).get();
        var topico = topicoRepository.findById(datos.topicId()).get();

        var fechaCreacion = LocalDate.now();

        var respuesta = new Respuesta(null, datos.mensaje(), creador, topico, fechaCreacion,
                fechaCreacion, false);
        respuestaRepository.save(respuesta);

        return new DatosDetalleRespuesta(respuesta);

    }



}
