package com.alura.forohub.domain.topico.evaluacion;

import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Clase TopicoEvaluacion: Es la entidad JPA que se comunicará con la DB
 *
 * @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode pertenecen a Lombok
 *
 * IMPORTANTE: Los nombres de los atributos de la Clase TopicoEvaluacion deben coincidir con
 * los nombres de las columnas de la tabla topico_likes_dislikes pero en formato Camel Case.
 *
 */
@Table(name = "topico_evaluacion")
@Entity(name = "TopicoEvaluacion")
@Getter                         // Crea de forma automática todos los Getters
@NoArgsConstructor              // Método Constructor que ayuda a instanciar sin argumentos - Esto es un prerrequisito
@AllArgsConstructor             // Método Constructor que ayuda a instanciar con todos los atributos
@EqualsAndHashCode(of = "id")   // Dos objetos de la misma clase son iguales a través del campo "id" de la tabla topico_likes_dislikes
public class TopicoEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoEvaluacion tipoEvaluacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;


    /**
     * Método de actualización de la información de un TopicoEvaluacion
     * @param datos
     */
    public void actualizarInformaciones(@Valid DatosActualizacionTopicoEvaluacion datos) {
        // Las siguientes líneas de código verifican que los datos sean correctos para la actualización del registro en la tabla

        if(datos.tipoEvaluacion() != null){
            // Validación que valor de una variable esté en un ENUM
            try {
                TipoEvaluacion tipoEvaluacionEnum = TipoEvaluacion.valueOf(datos.tipoEvaluacion().toString().toUpperCase());
                this.tipoEvaluacion = datos.tipoEvaluacion();

            } catch(IllegalArgumentException e){
                System.out.println("Error: El tipo de evaluación recibido: " + datos.tipoEvaluacion() + " es inválido");
            }
        }
    }
}


