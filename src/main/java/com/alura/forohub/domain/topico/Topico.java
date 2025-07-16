package com.alura.forohub.domain.topico;

import jakarta.persistence.*;
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
@EqualsAndHashCode(of = "id")   // Dos objetos de la misma clase son iguales a través del "id"
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Long idUsuario;
    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.id = null ;
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = datosRegistroTopico.fecha();
        this.status = datosRegistroTopico.status();
        this.idUsuario= datosRegistroTopico.idUsuario();
        this.curso = datosRegistroTopico.curso();
    }

}
