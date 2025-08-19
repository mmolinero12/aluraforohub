package com.alura.forohub.controllers;

/*
    Este Controller recibe requests de la aplicación Cliente.
 */

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoRepository;
import com.alura.forohub.domain.curso.DatosDetalleCurso;
import com.alura.forohub.domain.curso.DatosRegistroCurso;
import com.alura.forohub.domain.usuario.DatosDetalleUsuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")  // Para usarse con Swagger
public class CursoController {

    // Instanciar para poder guardar en
    @Autowired
    private CursoRepository cursoRepository;

    // ******************* REGISTRAR UN NUEVO CURSO *******************
    @Transactional
    @PostMapping
    public ResponseEntity registrarUsuario(
            @RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
            UriComponentsBuilder uriComponentsBuilder){

        // Se va a devolver el Código 201, Body del registro que se insertó y un Header Location

        var curso = new Curso(datosRegistroCurso);
        cursoRepository.save(curso);     //Registrar en la DB. con .save(), automáticamente topico contiene su id_usuario

        var uri = uriComponentsBuilder
                .path("/cursos/{id}")
                .buildAndExpand(curso.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }


}
