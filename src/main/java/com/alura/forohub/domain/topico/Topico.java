package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.respuesta.DatosActualizacionRespuesta;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
