package com.alura.forohub.controllers;

import com.alura.forohub.domain.ValidacionException;
import com.alura.forohub.domain.respuesta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/*
    Este Controller recibe requests de la aplicación Cliente.
 */

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RegistroDeRespuestas registroDeRespuestas;

    @Autowired
    private RespuestaRepository respuestaRepository;

    // ******************* REGISTRAR UNA NUEVA RESPUESTA *******************
    @Transactional
    @PostMapping
    public ResponseEntity registrarRespuesta(
            @RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta){

        var detalleRespuesta = registroDeRespuestas.registrar(datosRegistroRespuesta);

        return ResponseEntity.ok(detalleRespuesta);
    }

    // ******************* LISTAR RESPUESTAS *******************
    @GetMapping
    public ResponseEntity<Page<DatosListaRespuesta>> listarRespuestas(
            @PageableDefault(size = 2, sort={"mensaje"}) Pageable paginacion){
        var page = respuestaRepository
                .findAll(paginacion) //Con paginacion, nos devuelve un Page
                .map(DatosListaRespuesta::new);
        return ResponseEntity.ok(page);
    }

    // ******************* ACTUALIZAR UNA RESPUESTA *******************
    @Transactional
    @PutMapping
    public ResponseEntity actualizarRespuesta(
            @RequestBody @Valid DatosActualizacionRespuesta datos){

        if(!respuestaRepository.existsById(datos.answerId())){
            throw new ValidacionException("No existe una respuesta con el id proporcionado");
        }

        var respuesta = respuestaRepository.getReferenceById(datos.answerId());
        // Segunda opción
        // var respuesta = respuestaRepository.findById(datosActualizacionRespuesta.answerId()).get();
        respuesta.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    // ******************* ELIMINAR UNA RESPUESTA *******************
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ******************* LISTAR UNA ÚNICA RESPUESTA *******************
    @GetMapping("/{id}")
    public ResponseEntity<DatosListaRespuesta> listarUnicaRespuesta(@PathVariable Long id){
        var consulta = new DatosListaRespuesta(respuestaRepository.getReferenceById(id));
        return ResponseEntity.ok(consulta);
    }

}
