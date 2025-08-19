package com.alura.forohub.domain.curso;


import com.alura.forohub.domain.direccion.Direccion;
import com.alura.forohub.domain.usuario.DatosActualizacionUsuario;
import com.alura.forohub.domain.usuario.DatosRegistroUsuario;
import com.alura.forohub.domain.usuario.Perfil;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Clase Curso: Es la entidad JPA que se comunicará con la DB
 * @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode pertenecen a Lombok
 * IMPORTANTE: Los nombres de los atributos de la Clase Curso deben coincidir con
 * los nombres de las columnas de la tabla cursos pero en formato Camel Case.
 */
@Table(name = "cursos")
@Entity(name = "Curso")
@Getter                         // Crea de forma automática todos los Getters
@NoArgsConstructor              // Método Constructor que ayuda a instanciar sin argumentos - Esto es un prerrequisito
@AllArgsConstructor             // Método Constructor que ayuda a instanciar con todos los atributos
@EqualsAndHashCode(of = "id")   // Dos objetos de la misma clase son iguales a través del campo "id" de la tabla cursos
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    //private String aula;
    //private String clase;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    /**
     * Método Constructor de la Clase Curso
     * @param datos
     */
    public Curso(DatosRegistroCurso datos) {
        this.id = null;
        this.nombre = datos.nombre();
        //this.aula = datos.aula();
        //this.clase = datos.clase();
        this.categoria = datos.categoria();
    }

    /**
     * Método de actualización de la información de un curso
     * @param datos
     */
    public void actualizarInformaciones(@Valid DatosActualizacionCurso datos) {
        // Las siguientes líneas de código verifican que los datos sean correctos para la actualización del registro en la tabla
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }

    }

}
