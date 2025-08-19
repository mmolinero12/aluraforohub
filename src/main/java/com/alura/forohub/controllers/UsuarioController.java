package com.alura.forohub.controllers;


/*
    Este Controller recibe requests de la aplicación Cliente.
 */

import com.alura.forohub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // Instanciar para poder guardar en
    @Autowired
    private UsuarioRepository usuarioRepository;


    // ******************* REGISTRO DE UN NUEVO USUARIO *******************
    @Transactional
    @PostMapping
    public ResponseEntity registrarUsuario(
            @RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
            UriComponentsBuilder uriComponentsBuilder){

        // Se va a devolver el Código 201, Body del registro que se insertó y un Header Location

        var usuario = new Usuario(datosRegistroUsuario);
        usuarioRepository.save(usuario);     //Registrar en la DB. con .save(), automáticamente topico contiene su id_usuario

        var uri = uriComponentsBuilder
                .path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }


    // ******************* LISTAR USUARIOS *******************
    @GetMapping
    public ResponseEntity<Page<DatosListaUsuario>> listarUsuarios(@PageableDefault(size = 2, sort={"username"}) Pageable paginacion){
        var page = usuarioRepository
                .findAll(paginacion) //Con paginacion, nos devuelve un Page
                .map(DatosListaUsuario::new);
        return ResponseEntity.ok(page);
    }

    // ******************* ACTUALIZAR UN USUARIO *******************
    @Transactional
    @PutMapping
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizacionUsuario datosActualizacionUsuario){
        var usuario = usuarioRepository.getReferenceById(datosActualizacionUsuario.userId()); // PROBLEMA
        usuario.actualizarInformaciones(datosActualizacionUsuario);
        return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
    }


    // ******************* ELIMINAR UN USUARIO *******************
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.eliminarUsuario();
        return ResponseEntity.noContent().build();
    }

    // ******************* LISTAR UN ÚNICO USUARIO *******************
    @GetMapping("/{id}")
    public ResponseEntity<DatosListaUsuario> listarUnicoUsuario(@PathVariable Long id){
        var consulta = new DatosListaUsuario(usuarioRepository.getReferenceById(id));
        return ResponseEntity.ok(consulta);
    }

}
