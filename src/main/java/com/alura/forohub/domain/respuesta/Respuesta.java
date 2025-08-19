package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.respuesta.evaluacion.RespuestaEvaluacion;
import com.alura.forohub.domain.topico.Topico;
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
 * Clase Respuesta: Es la entidad JPA que se comunicará con la DB
 *
 * @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode pertenecen a Lombok
 *
 * IMPORTANTE: Los nombres de los atributos de la Clase Respuesta deben coincidir con
 * los nombres de las columnas de la tabla respuestas pero en formato Camel Case.
 *
 *  Se prefirió el que el servidor estableciera la fecha y hora actual y NO la aplicación cliente
 *  Se requieren dos variables de fecha, una para la fecha de creación y otra para la fecha de la última actualización
 *
 */
@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter                         // Crea de forma automática todos los Getters
@NoArgsConstructor              // Método Constructor que ayuda a instanciar sin argumentos - Esto es un prerrequisito
@AllArgsConstructor             // Método Constructor que ayuda a instanciar con todos los atributos
@EqualsAndHashCode(of = "id")   // Dos objetos de la misma clase son iguales a través del campo "id" de la tabla respuestas
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creador_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDate fechaCreacion;
    private LocalDate fechaUltimaActualizacion;
    private Boolean solucion;

    // Nueva relacion para respuestas con eliminacion en cascada - Se debe escribir el Método Constructor sin usar
    // Lombok ya que el Constructor de Lombok lo incluiría. Así lo descartaríamos al momento de guardar en la tabla
    // pero se conserva la relación en cascada
    @OneToMany(mappedBy = "respuesta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespuestaEvaluacion> respuestaEvaluaciones;

    public Respuesta(Long id, String mensaje, Usuario usuario, Topico topico, LocalDate fechaCreacion,
                     LocalDate fechaUltimaActualizacion, Boolean solucion) {
        this.id = id;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.topico = topico;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.solucion = solucion;
    }

    /**
     * Método de actualización de la información de una respuesta
     * @param datosActualizacionRespuesta
     */
    public void actualizarInformaciones(@Valid DatosActualizacionRespuesta datosActualizacionRespuesta) {
        // Las siguientes líneas de código verifican que datos llegaron para su actualización

        if(datosActualizacionRespuesta.mensaje() != null){
            mensaje = datosActualizacionRespuesta.mensaje();
            fechaUltimaActualizacion = LocalDate.now();

        }

    }
}
