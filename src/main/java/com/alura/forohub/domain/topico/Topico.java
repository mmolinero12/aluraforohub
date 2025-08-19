package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.respuesta.DatosActualizacionRespuesta;
import com.alura.forohub.domain.respuesta.Respuesta;
import com.alura.forohub.domain.topico.evaluacion.TopicoEvaluacion;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase Topico: Es la entidad JPA que se comunicará con la DB
 *
 * @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode pertenecen a Lombok
 *
 * IMPORTANTE: Los nombres de los atributos de la Clase Topico deben coincidir con
 * los nombres de las columnas de la tabla topicos pero en formato Camel Case.
 *
 * Se prefirió el que el servidor estableciera la fecha y hora actual y NO la aplicación cliente
 * Se requieren dos variables de fecha, una para la fecha de creación y otra para la fecha de la última actualización
 *
 */
@Table(name = "topicos")
@Entity(name = "Topico")
@Getter                         // Crea de forma automática todos los Getters
@NoArgsConstructor              // Método Constructor que ayuda a instanciar sin argumentos - Esto es un prerrequisito
@AllArgsConstructor             // Método Constructor que ayuda a instanciar con todos los atributos
@EqualsAndHashCode(of = "id")   // Dos objetos de la misma clase son iguales a través del campo "id" de la tabla topicos
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fechaCreacion;
    private LocalDate fechaUltimaActualizacion;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creador_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // Nueva relacion para respuestas con eliminacion en cascada - Se debe escribir el Método Constructor sin usar
    // Lombok ya que el Constructor de Lombok lo incluiría. Así lo descartaríamos al momento de guardar en la tabla
    // pero se conserva la relación en cascada
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;

    // Nueva relacion para evaluaciones de topicos con eliminacion en cascada
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TopicoEvaluacion> topicoEvaluaciones;

    public Topico( Long id, String titulo, String mensaje, LocalDate fechaCreacion,
                   LocalDate fechaUltimaActualizacion, Status status, Usuario usuario, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.status = status;
        this.usuario = usuario;
        this.curso = curso;
    }

    /**
     * Método de actualización de la información de un topico
     * @param datosActualizacionTopico
     */
    public void actualizarInformaciones(@Valid DatosActualizacionTopico datosActualizacionTopico) {
        // Las siguientes líneas de código verifican que datos llegaron para su actualización

        if(datosActualizacionTopico.titulo() != null){
            titulo = datosActualizacionTopico.titulo();
            fechaUltimaActualizacion = LocalDate.now();
        }

        if(datosActualizacionTopico.mensaje() != null){
            mensaje = datosActualizacionTopico.mensaje();
            fechaUltimaActualizacion = LocalDate.now();
        }

        if(datosActualizacionTopico.status() != null){
            status = datosActualizacionTopico.status();
            fechaUltimaActualizacion = LocalDate.now();
        }

    }

}
