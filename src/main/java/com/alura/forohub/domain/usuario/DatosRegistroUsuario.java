package com.alura.forohub.domain.usuario;

/*
Esto es un DTO - Data Transfer Object, el cual permite encapsular los datos que recibimos y enviamos
 */

import com.alura.forohub.domain.direccion.DatosDireccion;
import com.alura.forohub.domain.topico.Curso;
import com.alura.forohub.domain.topico.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroUsuario(
        // Los siguientes nombres de variables deben coincidir con las "Keys"
        // del Json que se recibe
        // @NotBlank es específico de String
        @NotBlank (message = "Nombre es obligatorio") String nombre,
        @NotBlank (message = "Apellido Paterno es obligatorio") String apellido_paterno,
        @NotBlank (message = "Apellido Materno es obligatorio") String apellido_materno,
        @NotNull (message = "Fecha de Nacimiento es obligatoria")
        @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
        String fecha_nacimiento,
        String genero,
        @NotNull(message = "Dirección es obligatoria") @Valid DatosDireccion direccion,

        @NotBlank @Email(message = "E-mail es obligatorio") String email,
        @NotNull (message = "Teléfono es obligatorio")
        @Pattern(regexp = "^\\(\\d{2,3}\\)\\s\\d{3,4}-\\d{4}$")   //(55) 5658-1234
        String telefono,
        @NotBlank (message = "El username es obligatorio") String username,
        @NotBlank (message = "La contraseña es obligatoria. Y debe ser al menos una letra mayúscula, una letra minúscula, un dígito, un carácter especial, y entre 8 y 12 caracteres")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,12}$") String password,
        Perfil perfil
) {
}
