package com.alura.forohub.controllers;

import com.alura.forohub.domain.ValidacionException;
import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.topico.evaluacion.*;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
    Este Controller recibe requests de la aplicación Cliente.
 */

@RestController
@RequestMapping("/topicoevaluaciones")
@SecurityRequirement(name = "bearer-key") // Para usarse con Swagger
public class TopicoEvaluacionController {

    @Autowired
    private TopicoEvaluacionRepository topicoEvaluacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;


    @Transactional
    @PostMapping
    public ResponseEntity<?> registrarTopicoEvaluacion(
            @RequestBody @Valid DatosRegistroTopicoEvaluacion datos){
        // Usar ResponseEntity<?> (conocido como wildcard) es la solución más limpia y recomendada. Le indica a
        // Spring que el método puede devolver un ResponseEntity que contiene cualquier tipo de objeto en su
        // cuerpo (<String>, <DatosDetalleTopicoEvaluacion>, etc.). Esto te permite manejar diferentes tipos de
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

        var topicoExistsFlag = topicoRepository.existsById(datos.topicoId());
        var usuarioExistsFlag = usuarioRepository.existsById(datos.usuarioId());
        var topicoEvaluacionExistsFlag = topicoEvaluacionRepository.existsByUsuarioIdAndTopicoId(datos.usuarioId(), datos.topicoId());
        TopicoEvaluacion topicoEvaluacion;

        // 1. Validaciones "fail-fast"
        if (!topicoRepository.existsById(datos.topicoId())) {
            return ResponseEntity.notFound().build();
        }

        if (!usuarioRepository.existsById(datos.usuarioId())) {
            return ResponseEntity.notFound().build();
        }

        if (topicoEvaluacionRepository.existsByUsuarioIdAndTopicoId(datos.usuarioId(), datos.topicoId())) {
            return ResponseEntity.badRequest().body("Ya existe una evaluación. Si desea modificarla, use la funcionalidad de actualización.");
        }

        // 2. Lógica de negocio principal
        var usuario = usuarioRepository.findById(datos.usuarioId()).orElseThrow(
                () -> new ValidacionException("Usuario no encontrado"));
        var topico = topicoRepository.findById(datos.topicoId()).orElseThrow(
                () -> new ValidacionException("Tópico no encontrado"));

        topicoEvaluacion = new TopicoEvaluacion(null, datos.tipoEvaluacion(), usuario, topico);

        // 3. Persistir y devolver
        topicoEvaluacion = topicoEvaluacionRepository.save(topicoEvaluacion);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DatosDetalleTopicoEvaluacion(topicoEvaluacion));

        /*  Código anterior

        if(!topicoExistsFlag){
            throw new ValidacionException("No existe un tópico con el id proporcionado");
        }

        if(!usuarioExistsFlag){
            throw new ValidacionException("No existe un usuario con el id proporcionado");
        }

        if(!topicoEvaluacionExistsFlag){
            var usuario = usuarioRepository.findById(datos.usuarioId()).get();
            var topico = topicoRepository.findById(datos.topicoId()).get();

            topicoEvaluacion = new TopicoEvaluacion(null,
                    datos.tipoEvaluacion(), usuario, topico);

            topicoEvaluacionRepository.save(topicoEvaluacion);

        } else {
            throw new ValidacionException("Ya existe una evaluación - Si desea modificar, use la Actualización de la Evaluación");
        }

        return ResponseEntity.ok(new DatosDetalleTopicoEvaluacion(topicoEvaluacion));

         */
    }


    @GetMapping
    public ResponseEntity<Page<DatosListaTopicoEvaluacion>> listarTopicoEvaluacion(@PageableDefault(size = 2, sort={"usuario.username"}) Pageable paginacion){
        var page = topicoEvaluacionRepository
                .findAll(paginacion) //Con paginacion, nos devuelve un Page
                .map(DatosListaTopicoEvaluacion::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DatosDetalleTopicoEvaluacion> actualizarTopicoEvaluacion(
            @RequestBody @Valid DatosActualizacionTopicoEvaluacion datos){
        /*
         * Tipado Genérico: Usas ResponseEntity<DatosDetalleTopicoEvaluacion>, lo que especifica el tipo de objeto que
         * esperas devolver. Esto mejora la seguridad del código y facilita la detección de errores en tiempo de compilación.
         */
        var topicoExistsFlag = topicoRepository.existsById(datos.topicoId());
        var usuarioExistsFlag = usuarioRepository.existsById(datos.usuarioId());
        var topicoEvaluacionExistsFlag = topicoEvaluacionRepository.existsByUsuarioIdAndTopicoId(datos.usuarioId(), datos.topicoId());

        /*
         *
         * Estrategia "early return" o "fail-fast" de manejo de condicionales y return
         * Consiste en validar las condiciones negativas al inicio del método y devolver la respuesta de inmediato si no se cumplen.
         *
         * Manejo de "No Encontrado": En lugar de lanzar una excepción, que es más costoso y puede complicar el manejo de errores del
         * cliente, ahora devuelves un ResponseEntity.notFound().build(). Esto es una práctica estándar en APIs RESTful para
         * indicar que el recurso no existe, y corresponde al código de estado HTTP 404.
         *
         */
        // 1. Validaciones "fail-fast"
        if (!topicoRepository.existsById(datos.topicoId())) {
            return ResponseEntity.notFound().build();
        }

        if (!usuarioRepository.existsById(datos.usuarioId())) {
            return ResponseEntity.notFound().build();
        }

        // 2. Lógica de negocio principal
        TopicoEvaluacion topicoEvaluacion = topicoEvaluacionRepository
                .findTopicoEvaluacion(datos.usuarioId(), datos.topicoId());

        if (topicoEvaluacion == null) {
            // En este caso, la combinación de usuario y tópico no existe
            return ResponseEntity.notFound().build();
        }

        topicoEvaluacion.actualizarInformaciones(datos);

        // 3. Respuesta final (éxito)
        return ResponseEntity.ok(new DatosDetalleTopicoEvaluacion(topicoEvaluacion));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopicoEvaluacion(@PathVariable Long id){
        topicoEvaluacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // retorna la respuesta:  204 No Content
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaTopicoEvaluacion> listarUnicoTopicoEvaluacion(@PathVariable Long id){

        // Opción 1 - La más recomendable
        var consulta = topicoEvaluacionRepository.getReferenceById(id);     // La responsabilidad de obtener la entidad recae en el repositorio
        return ResponseEntity.ok(new DatosListaTopicoEvaluacion(consulta)); // Construcción del DTO

        /* Opción 2:
        var consulta = new DatosListaTopicoEvaluacion(topicoEvaluacionRepository.getReferenceById(id));
        return ResponseEntity.ok(consulta);
         */
    }




}
