package com.alura.forohub.controllers;

import com.alura.forohub.domain.topico.*;
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
@RequestMapping("/topicos")
public class TopicoController {

    // Instanciar para poder guardar en
    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder){

        // Se va a devolver el Código 201, Body del registro que se insertó y un Header Location

        var topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);     //Registrar en la DB. con .save(), automáticamente topico contiene su id_topico

        var uri = uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId_topico())
                .toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size = 2, sort={"titulo"}) Pageable paginacion){
        var page =  topicoRepository
                .findAll(paginacion) //Con paginacion, nos devuelve un Page
                .map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizacionTopico datosActualizacionTopico){
        var topico = topicoRepository.getReferenceById(datosActualizacionTopico.id_topico());
        topico.actualizarInformaciones(datosActualizacionTopico);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // retorna la respuesta:  204 No Content
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaTopico> listarUnicoTopico(@PathVariable Long id){

        /*
        Opción 1
        var consulta = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosListaTopico(consulta));
         */

        var consulta = new DatosListaTopico(topicoRepository.getReferenceById(id));
        return ResponseEntity.ok(consulta);

    }



}
