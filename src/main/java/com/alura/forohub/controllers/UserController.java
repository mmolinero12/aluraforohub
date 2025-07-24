package com.alura.forohub.controllers;


/*
    Este Controller recibe requests de la aplicación Cliente.
 */

import com.alura.forohub.domain.topico.DatosActualizacionTopico;
import com.alura.forohub.domain.topico.DatosListaTopico;
import com.alura.forohub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    // Instanciar para poder guardar en
    @Autowired
    private UsuarioRepository usuarioRepository;

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
                .buildAndExpand(usuario.getId_usuario())
                .toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaFullUsuario>> listarUsuarios(@PageableDefault(size = 2, sort={"username"}) Pageable paginacion){
        var page = usuarioRepository
                .findAll(paginacion) //Con paginacion, nos devuelve un Page
                .map(DatosListaFullUsuario::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizacionUsuario datosActualizacionUsuario){
        var usuario = usuarioRepository.getReferenceById(datosActualizacionUsuario.id_usuario());
        usuario.actualizarInformaciones(datosActualizacionUsuario);
        return ResponseEntity.ok(new DatosDetalleUsuario(usuario));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.eliminarUsuario();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaFullUsuario> listarUnicoUsuario(@PathVariable Long id){
        var consulta = new DatosListaFullUsuario(usuarioRepository.getReferenceById(id));
        return ResponseEntity.ok(consulta);
    }

}
