package com.alura.forohub.domain.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


/**
 * Record de la dirección que se utilizará en el registro de nuevos usuarios.
 *
 * IMPORTANTE: Los nombres de los atributos deben coincidir con
 * los nombres de las columnas de la tabla usuarios pero en formato Camel Case.
 *
 * @param calle
 * @param numero
 * @param colonia
 * @param codigoPostal
 * @param ciudad
 * @param estado
 */
public record DatosDireccion(

        @NotBlank(message = "Calle es obligatoria") String calle,
        String numero,
        @NotBlank(message = "Colonia es obligatoria") String colonia,
        @NotBlank(message = "Código Postal es obligatorio")
        @Pattern(regexp = "\\d{5}", message = "El Código Postal debe contener 5 dígitos") String codigoPostal,
        @NotBlank(message = "Ciudad es obligatorio") String ciudad,
        @NotBlank(message = "Estado es obligatorio") String estado

) {
}
