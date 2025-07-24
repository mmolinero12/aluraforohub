package com.alura.forohub.domain.usuario;


import com.alura.forohub.domain.direccion.Direccion;
import com.alura.forohub.domain.topico.Status;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
public class Usuario {
    // Los nombres de los atributos de la Clase Topico deben coincidir con
    // los nombres de las columnas de la tabla topicos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private Boolean activo;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private LocalDate fecha_nacimiento;
    private String genero;
    @Embedded
    private Direccion direccion;
    private String email;
    private String telefono;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Usuario(DatosRegistroUsuario datos) {
        this.id_usuario = null;
        this.activo = true;
        this.nombre = datos.nombre();
        this.apellido_paterno = datos.apellido_paterno();
        this.apellido_materno = datos.apellido_materno();
        this.fecha_nacimiento = LocalDate.parse(datos.fecha_nacimiento());
        this.genero = datos.genero();
        this.direccion = new Direccion(datos.direccion());
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.username = datos.username();
        this.password = datos.password();
        this.perfil = datos.perfil();
    }

    public void actualizarInformaciones(@Valid DatosActualizacionUsuario datos) {

        if(datos.activo() != null){
            this.activo = datos.activo();
        }

        if(datos.direccion() != null){
            this.direccion.actualizarDireccion(datos.direccion());
        }

        if(datos.telefono() != null){
            this.telefono = datos.telefono();
        }

        if(datos.password() != null){
            this.password = datos.password();
        }

        if(datos.perfil() != null){
            this.perfil = datos.perfil();
        }

    }

    public void eliminarUsuario() {
        this.activo = false;
    }

}
