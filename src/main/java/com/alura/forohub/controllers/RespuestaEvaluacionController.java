package com.alura.forohub.controllers;

import com.alura.forohub.domain.ValidacionException;
import com.alura.forohub.domain.respuesta.RespuestaRepository;
import com.alura.forohub.domain.respuesta.evaluacion.DatosRegistroRespuestaEvaluacion;
import com.alura.forohub.domain.respuesta.evaluacion.RespuestaEvaluacionRepository;
import com.alura.forohub.domain.respuesta.evaluacion.*;
import com.alura.forohub.domain.usuario.UsuarioRepository;
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
@RequestMapping("/respuestaevaluaciones")
public class RespuestaEvaluacionController {

    @Autowired
    private RespuestaEvaluacionRepository respuestaEvaluacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;


    @Transactional
    @PostMapping
    public ResponseEntity<?> registrarRespuestaEvaluacion(
            @RequestBody @Valid DatosRegistroRespuestaEvaluacion datos){
        // Usar ResponseEntity<?> (conocido como wildcard) es la solución más limpia y recomendada. Le indica a
        // Spring que el método puede devolver un ResponseEntity que contiene cualquier tipo de objeto en su
        // cuerpo (<String>, <DatosDetalleRespuestaEvaluacion>, etc.). Esto te permite manejar diferentes tipos de
        // respuestas (éxito con un DTO y error con un mensaje de texto) dentro del mismo método.

        /*
         * 1. Manejo de "Existencia" (if (respuestaEvaluacionRepository.exists...)): Usamos el "early return" para
         * manejar el caso de que la evaluación ya exista. Si es así, devolvemos un ResponseEntity.badRequest()
         * (código HTTP 400) con un mensaje claro, lo cual es más apropiado que un error de tipo NOT_FOUND y es
         * una buena práctica para indicar que la solicitud del cliente no es válida.
         * 2. Optional para findById: El método findById devuelve un Optional. Al usar .orElseThrow(...),
         * te aseguras de obtener el objeto o lanzar una excepción, evitando el uso de .get() que puede causar
         * una NoSuchElementException si el objeto no se encuentra. Aunque ya validamos la existencia al principio,
         *  usar orElseThrow es una práctica más robusta y segura.
         * 3. Manejo del return final: Ahora, la creación del objeto respuestaEvaluacion ocurre solo después de todas
         * las validaciones exitosas. Así, siempre que la lógica de negocio se ejecute, el objeto estará inicializado
         * y el return será seguro.
         * 4. Código de Estado CREATED: Para una operación POST (creación de un recurso), es una buena práctica
         * devolver un código de estado 201 (CREATED) en lugar de 200 (OK). Esto informa al cliente que un nuevo
         * recurso se ha creado exitosamente. Lo logramos con ResponseEntity.status(HttpStatus.CREATED).
         *
         */

        var respuestaExistsFlag = respuestaRepository.existsById(datos.respuestaId());
        var usuarioExistsFlag = usuarioRepository.existsById(datos.usuarioId());
        var respuestaEvaluacionExistsFlag = respuestaEvaluacionRepository.existsByUsuarioIdAndRespuestaId(datos.usuarioId(), datos.respuestaId());
        RespuestaEvaluacion respuestaEvaluacion;

        // 1. Validaciones "fail-fast"
        if (!respuestaRepository.existsById(datos.respuestaId())) {
            return ResponseEntity.notFound().build();
        }

        if (!usuarioRepository.existsById(datos.usuarioId())) {
            return ResponseEntity.notFound().build();
        }

        if (respuestaEvaluacionRepository.existsByUsuarioIdAndRespuestaId(datos.usuarioId(), datos.respuestaId())) {
            return ResponseEntity.badRequest().body("Ya existe una evaluación. Si desea modificarla, use la funcionalidad de actualización.");
        }

        // 2. Lógica de negocio principal
        var usuario = usuarioRepository.findById(datos.usuarioId()).orElseThrow(
                () -> new ValidacionException("Usuario no encontrado"));
        var respuesta = respuestaRepository.findById(datos.respuestaId()).orElseThrow(
                () -> new ValidacionException("Tópico no encontrado"));

        respuestaEvaluacion = new RespuestaEvaluacion(null, datos.tipoEvaluacion(), usuario, respuesta);

        // 3. Persistir y devolver
        respuestaEvaluacion = respuestaEvaluacionRepository.save(respuestaEvaluacion);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DatosDetalleRespuestaEvaluacion(respuestaEvaluacion));

        /*  Código anterior

        if(!respuestaExistsFlag){
            throw new ValidacionException("No existe un tópico con el id proporcionado");
        }

        if(!usuarioExistsFlag){
            throw new ValidacionException("No existe un usuario con el id proporcionado");
        }

        if(!respuestaEvaluacionExistsFlag){
            var usuario = usuarioRepository.findById(datos.usuarioId()).get();
            var respuesta = respuestaRepository.findById(datos.respuestaId()).get();

            respuestaEvaluacion = new RespuestaEvaluacion(null,
                    datos.tipoEvaluacion(), usuario, respuesta);

            respuestaEvaluacionRepository.save(respuestaEvaluacion);

        } else {
            throw new ValidacionException("Ya existe una evaluación - Si desea modificar, use la Actualización de la Evaluación");
        }

        return ResponseEntity.ok(new DatosDetalleRespuestaEvaluacion(respuestaEvaluacion));

         */
    }


    @GetMapping
    public ResponseEntity<Page<DatosListaRespuestaEvaluacion>> listarRespuestaEvaluacion(@PageableDefault(size = 2, sort={"usuario.username"}) Pageable paginacion){
        var page = respuestaEvaluacionRepository
                .findAll(paginacion) //Con paginacion, nos devuelve un Page
                .map(DatosListaRespuestaEvaluacion::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DatosDetalleRespuestaEvaluacion> actualizarRespuestaEvaluacion(
            @RequestBody @Valid DatosActualizacionRespuestaEvaluacion datos){
        /*
         * Tipado Genérico: Usas ResponseEntity<DatosDetalleRespuestaEvaluacion>, lo que especifica el tipo de objeto que
         * esperas devolver. Esto mejora la seguridad del código y facilita la detección de errores en tiempo de compilación.
         */
        var respuestaExistsFlag = respuestaRepository.existsById(datos.respuestaId());
        var usuarioExistsFlag = usuarioRepository.existsById(datos.usuarioId());
        var respuestaEvaluacionExistsFlag = respuestaEvaluacionRepository.existsByUsuarioIdAndRespuestaId(datos.usuarioId(), datos.respuestaId());

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
        if (!respuestaRepository.existsById(datos.respuestaId())) {
            return ResponseEntity.notFound().build();
        }

        if (!usuarioRepository.existsById(datos.usuarioId())) {
            return ResponseEntity.notFound().build();
        }

        // 2. Lógica de negocio principal
        RespuestaEvaluacion respuestaEvaluacion = respuestaEvaluacionRepository
                .findRespuestaEvaluacion(datos.usuarioId(), datos.respuestaId());

        if (respuestaEvaluacion == null) {
            // En este caso, la combinación de usuario y tópico no existe
            return ResponseEntity.notFound().build();
        }

        respuestaEvaluacion.actualizarInformaciones(datos);

        // 3. Respuesta final (éxito)
        return ResponseEntity.ok(new DatosDetalleRespuestaEvaluacion(respuestaEvaluacion));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuestaEvaluacion(@PathVariable Long id){
        respuestaEvaluacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // retorna la respuesta:  204 No Content
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaRespuestaEvaluacion> listarUnicoRespuestaEvaluacion(@PathVariable Long id){

        // Opción 1 - La más recomendable
        var consulta = respuestaEvaluacionRepository.getReferenceById(id);     // La responsabilidad de obtener la entidad recae en el repositorio
        return ResponseEntity.ok(new DatosListaRespuestaEvaluacion(consulta)); // Construcción del DTO

        /* Opción 2:
        var consulta = new DatosListaRespuestaEvaluacion(respuestaEvaluacionRepository.getReferenceById(id));
        return ResponseEntity.ok(consulta);
         */
    }




}
