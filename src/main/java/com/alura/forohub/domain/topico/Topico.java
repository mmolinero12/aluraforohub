package com.alura.forohub.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Esta Clase Topico es en realidad la entidad JPA que se comunicará con la DB
// @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode pertenecen a Lombok

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter                         // Crea de forma automática todos los Getters
@NoArgsConstructor              // Método Constructor que ayuda a instanciar sin argumentos
@AllArgsConstructor             // Método Constructor que ayuda a instanciar con todos los atributos
@EqualsAndHashCode(of = "id_topico")   // Dos objetos de la misma clase son iguales a través del "id"
public class Topico {
    // Los nombres de los atributos de la Clase Topico deben coincidir con
    // los nombres de las columnas de la tabla topicos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_topico;
    private String titulo;
    private String mensaje;
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Long id_usuario;
    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.id_topico = null ;
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = LocalDate.parse(datosRegistroTopico.fecha());
        this.status = datosRegistroTopico.status();
        this.id_usuario = datosRegistroTopico.idUsuario();
        this.curso = datosRegistroTopico.curso();
    }

    public void actualizarInformaciones(@Valid DatosActualizacionTopico datosActualizacionTopico) {
        // Las siguientes líneas de código verifican que datos llegaron para su actualización
        if(datosActualizacionTopico.titulo() != null){
            this.titulo = datosActualizacionTopico.titulo();
        }
        if(datosActualizacionTopico.mensaje() != null){
            this.mensaje = datosActualizacionTopico.mensaje();
        }
        if(datosActualizacionTopico.status() != null){
            this.status = datosActualizacionTopico.status();
        }
    }
}
