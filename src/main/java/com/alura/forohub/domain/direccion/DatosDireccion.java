package com.alura.forohub.domain.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosDireccion(

        @NotBlank(message = "Calle es obligatoria") String calle,
        String numero,
        @NotBlank(message = "Colonia es obligatoria") String colonia,
        @NotBlank(message = "Código Postal es obligatorio")
        @Pattern(regexp = "\\d{5}", message = "El Código Postal debe contener 5 dígitos") String codigo_postal,
        @NotBlank(message = "Ciudad es obligatorio") String ciudad,
        @NotBlank(message = "Estado es obligatorio") String estado

) {
}
