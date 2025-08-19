package com.alura.forohub.domain.usuario;

import com.alura.forohub.domain.direccion.Direccion;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Clase Usuario: Es la entidad JPA que se comunicará con la DB
 * @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode pertenecen a Lombok
 * IMPORTANTE: Los nombres de los atributos de la Clase Usuario deben coincidir con
 * los nombres de las columnas de la tabla usuarios pero en formato Camel Case.
 */
@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter                         // Crea de forma automática todos los Getters
@NoArgsConstructor              // Método Constructor que ayuda a instanciar sin argumentos - Esto es un prerrequisito
@AllArgsConstructor             // Método Constructor que ayuda a instanciar con todos los atributos
@EqualsAndHashCode(of = "id")   // Dos objetos de la misma clase son iguales a través del campo "id" de la tabla topicos
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String genero;
    @Embedded
    private Direccion direccion;
    @Column(unique = true)
    private String email;
    private String telefono;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;


    // Static helper method to create a password encoder
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Método Constructor de la Clase Usuario
     * @param datos
     */
    public Usuario(DatosRegistroUsuario datos) {

        this.id = null;
        this.activo = true;
        this.nombre = datos.nombre();
        this.apellidoPaterno = datos.apellidoPaterno();
        this.apellidoMaterno = datos.apellidoMaterno();
        this.fechaNacimiento = LocalDate.parse(datos.fechaNacimiento());
        this.genero = datos.genero();
        this.direccion = new Direccion(datos.direccion());
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.username = datos.username();
        this.password = datos.password();
        this.perfil = datos.perfil();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método de actualización de la información de un usuario
     * @param datos
     */
    public void actualizarInformaciones(@Valid DatosActualizacionUsuario datos) {
        // Las siguientes líneas de código verifican que los datos sean correctos para la actualización del registro en la tabla
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

    // Métodos implements UserDetails
    // No van a aparecer getPassword() ni getUsername(), porque se utilizaron los mismos nombres de la Clase Madre.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
