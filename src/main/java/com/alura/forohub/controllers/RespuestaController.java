package com.alura.forohub.controllers;

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

    // Instanciar para poder guardar en
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrarRespuesta(
            @RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
            UriComponentsBuilder uriComponentsBuilder){

        // Se va a devolver el Código 201, Body del registro que se insertó y un Header Location

        var respuesta = new Respuesta(datosRegistroRespuesta);
        respuestaRepository.save(respuesta); //Registrar en la DB. con .save(), automáticamente respuesta contiene su id_respuesta

        var uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId_respuesta()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleRespuesta(respuesta));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaRespuesta>> listarRespuestas(@PageableDefault(size = 2, sort={"mensaje"}) Pageable paginacion){
        var page = respuestaRepository
                .findAll(paginacion) //Con paginacion, nos devuelve un Page
                .map(DatosListaRespuesta::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosActualizacionRespuesta datosActualizacionRespuesta){
        var respuesta = respuestaRepository.getReferenceById(datosActualizacionRespuesta.id_respuesta());
        respuesta.actualizarInformaciones(datosActualizacionRespuesta);

        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaRespuesta> listarUnicaRespuesta(@PathVariable Long id){
        var consulta = new DatosListaRespuesta(respuestaRepository.getReferenceById(id));
        return ResponseEntity.ok(consulta);
    }

}
