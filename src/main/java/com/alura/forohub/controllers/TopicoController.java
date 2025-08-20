package com.alura.forohub.controllers;

import com.alura.forohub.domain.ValidacionException;
import com.alura.forohub.domain.curso.Categoria;
import com.alura.forohub.domain.curso.CursoRepository;
import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Objects;

/*
    Este Controller recibe requests de la aplicación Cliente.
 */

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key") // Para usarse con Swagger
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository creadorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // ******************* REGISTRAR UN NUEVO TÓPICO *******************
    @Transactional
    @PostMapping
    public ResponseEntity<?> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datos,
            UriComponentsBuilder uriComponentsBuilder,
            Authentication authentication) { // Se inyecta el objeto Authentication

        // Usar ResponseEntity<?> (conocido como wildcard) es la solución más limpia y recomendada. Le indica a
        // Spring que el método puede devolver un ResponseEntity que contiene cualquier tipo de objeto en su
        // cuerpo (<String>, <DatosDetalleTopico>, etc.). Esto te permite manejar diferentes tipos de
        // respuestas (éxito con un DTO y error con un mensaje de texto) dentro del mismo método.

        /*
         * 1. Manejo de "Existencia" (if (topicoEvaluacionRepository.exists...)): Usamos el "early return" para
         * manejar el caso de que la evaluación ya exista. Si es así, devolvemos un ResponseEntity.badRequest()
         * (código HTTP 400) con un mensaje claro, lo cual es más apropiado que un error de tipo NOT_FOUND y es
         * una buena práctica para indicar que la solicitud del cliente no es válida.
         * 2. Optional para findById: El método findById devuelve un Optional. Al usar .orElseThrow(...),
         * te aseguras de obtener el objeto o lanzar una excepción, evitando el uso de .get() que puede causar
         * una NoSuchElementException si el objeto no se encuentra. Aunque ya validamos la existencia al principio,
         *  usar orElseThrow es una práctica más robusta y segura.
         * 3. Manejo del return final: Ahora, la creación del objeto topicoEvaluacion ocurre solo después de todas
         * las validaciones exitosas. Así, siempre que la lógica de negocio se ejecute, el objeto estará inicializado
         * y el return será seguro.
         * 4. Código de Estado CREATED: Para una operación POST (creación de un recurso), es una buena práctica
         * devolver un código de estado 201 (CREATED) en lugar de 200 (OK). Esto informa al cliente que un nuevo
         * recurso se ha creado exitosamente. Lo logramos con ResponseEntity.status(HttpStatus.CREATED).
         *
         */

        // Obtener el objeto del usuario autenticado
        var creador = (Usuario) authentication.getPrincipal();
        var cursoExistsFlag = cursoRepository.existsById(datos.cursoId());
        Topico topico;

        // 1. Validaciones "fail-fast"

        if(!cursoExistsFlag){
            return ResponseEntity.notFound().build();
        }

        // 2. Lógica de negocio principal
        var curso = cursoRepository.findById(datos.cursoId()).orElseThrow(
                () -> new ValidacionException("Curso no encontrado"));

        var fechaCreacion = LocalDate.now();
        topico =  new Topico(null, datos.titulo(), datos.mensaje(), fechaCreacion,
                fechaCreacion, creador, curso);

        // 3. Persistir y devolver
        topico = topicoRepository.save(topico); //Registrar en la DB. con .save(), automáticamente topico genera su id

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));

    }

    // ******************* LISTAR TÓPICOS *******************
    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(
            @RequestBody @Valid DatosOrdenacionTopico datos,
            Pageable paginacion){

        // Valores por omisión
        var pageSize = 2;
        Page<DatosListaTopico> page = null;
        String campo = "id";
        Sort.Direction direccion = Sort.Direction.ASC;
        Categoria categoria = null;

        // Datos provenientes del JSON Cliente
        var username = datos.criteriosDeBusqueda().username();
        var nombreCurso = datos.criteriosDeBusqueda().nombreCurso();
        var fechaCreacion = datos.criteriosDeBusqueda().fechaCreacion();
        categoria = datos.criteriosDeBusqueda().categoria();
        var campoName = datos.ordenamiento().campo();
        var direccionOrdenacion = datos.ordenamiento().direccionOrdenacion();

        // ********************       Validación

        // Recordar que NO se puede usar == para comparar Strings
        if(Objects.equals(direccionOrdenacion, "ASC")) { direccion = Sort.Direction.ASC; }
        if(Objects.equals(direccionOrdenacion, "DESC")) { direccion = Sort.Direction.DESC; }

        //El método estático Objects.equals() maneja la comparación de la siguiente manera:
        // * Si ambos objetos son null, devuelve true.
        // * Si solo uno de los objetos es null, devuelve false.
        // * Si ninguno es null, llama al método .equals() del primer objeto para realizar la comparación.
        // Con esto evitamos incurrir en una excepción si es null.

        if(Objects.equals(campoName, "username" ))      {   campo = "usuario.".concat("username"); }
        if(Objects.equals(campoName, "nombreCurso" ))   {   campo = "curso.".concat("nombre"); }
        if(Objects.equals(campoName, "fechaCreacion" )) {   campo = "fechaCreacion"; }
        if(Objects.equals(campoName, "categoria" ))     {   campo = "curso.".concat("categoria"); }

        if(username != null || nombreCurso != null || fechaCreacion != null || categoria != null){
            if(username != null){
                if(!creadorRepository.existsByUsername(username)){
                    throw new ValidacionException("No existe un usuario con el username proporcionado");
                } else {
                    page = topicoRepository.findByUsuarioUsername(username, paginacion).map(DatosListaTopico::new);
                }
            }

            if(nombreCurso != null){
                if(!cursoRepository.existsByNombreCurso(nombreCurso)){
                    throw new ValidacionException("No existe un curso con el nombre proporcionado");
                } else {
                    page = topicoRepository.findByCursoNombre(nombreCurso, paginacion).map(DatosListaTopico::new);
                }
            }

            if(fechaCreacion != null){
                if(!topicoRepository.existsByFechaCreacion(fechaCreacion)){
                    throw new ValidacionException("No existe un tópico con la fecha proporcionada");
                } else {
                    page = topicoRepository.findByFechaCreacion(fechaCreacion, paginacion).map(DatosListaTopico::new);
                }
            }

            if(categoria != null){
                try {
                    page = topicoRepository.findByCursoCategoria(categoria, paginacion).map(DatosListaTopico::new);
                } catch (IllegalArgumentException e) {
                    throw new ValidacionException("No existe una categoría con el nombre proporcionado");
                }
            }
        } else {
            paginacion = PageRequest.of(paginacion.getPageNumber(), pageSize, direccion, campo);
            page =  topicoRepository.findAll(paginacion).map(DatosListaTopico::new); //Con paginacion, nos devuelve un Page
        }

        return ResponseEntity.ok(page);
    }

    // ******************* ACTUALIZAR UN TÓPICO *******************
    @Transactional
    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_USER') and @topicoSecurity.isOwner(#id)")     //Validación para que el creador elimine el registro
    public ResponseEntity actualizarTopico(
            @RequestBody @Valid DatosActualizacionTopico datosActualizacionTopico){

        if(!topicoRepository.existsById(datosActualizacionTopico.topicId())){
            throw new ValidacionException("No existe un tópico con el id proporcionado");
        }

        var respuesta = topicoRepository.findById(datosActualizacionTopico.topicId()).get();
        // Segunda opción
        // var respuesta = topicoRepository.getReferenceById(datosActualizacionTopico.topicId());
        respuesta.actualizarInformaciones(datosActualizacionTopico);

        return ResponseEntity.ok(new DatosDetalleTopico(respuesta));
    }

    // ******************* ELIMINAR UN TÓPICO *******************
    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') and @topicoSecurity.isOwner(#id)")     //Validación para que el creador elimine el registro
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // retorna la respuesta:  204 No Content
    }


    // ******************* LISTAR UN ÚNICO TÓPICO *******************
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
