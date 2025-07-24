package com.alura.forohub.domain.respuesta;


// Esta Clase Topico es en realidad la entidad JPA que se comunicará con la DB
// @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode pertenecen a Lombok

import com.alura.forohub.domain.topico.DatosActualizacionTopico;
import com.alura.forohub.domain.topico.DatosRegistroTopico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter                         // Crea de forma automática todos los Getters
@NoArgsConstructor              // Método Constructor que ayuda a instanciar sin argumentos
@AllArgsConstructor             // Método Constructor que ayuda a instanciar con todos los atributos
@EqualsAndHashCode(of = "id_respuesta")   // Dos objetos de la misma clase son iguales a través del "id"
public class Respuesta {
    // Los nombres de los atributos de la Clase Respuesta deben coincidir con
    // los nombres de las columnas de la tabla respuestas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_respuesta;
    private String mensaje;
    private LocalDate fecha;
    private Long id_usuario;
    private Long id_topico;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
        this.id_respuesta = null ;
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.fecha = LocalDate.parse(datosRegistroRespuesta.fecha());
        this.id_usuario = datosRegistroRespuesta.idUsuario();
        this.id_topico = datosRegistroRespuesta.idTopico();
    }

    public void actualizarInformaciones(@Valid DatosActualizacionRespuesta datosActualizacionRespuesta) {
        // Las siguientes líneas de código verifican que datos llegaron para su actualización

        if(datosActualizacionRespuesta.mensaje() != null){
            this.mensaje = datosActualizacionRespuesta.mensaje();
        }

        if(datosActualizacionRespuesta.fecha() != null){
            this.fecha = LocalDate.parse(datosActualizacionRespuesta.fecha());
        }

    }
}
