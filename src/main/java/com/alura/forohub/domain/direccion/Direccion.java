package com.alura.forohub.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * IMPORTANTE: Los nombres de los atributos deben coincidir con
 * los nombres de las columnas de la tabla usuarios pero en formato Camel Case.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    private String ciudad;
    private String estado;

    /**
     * Método Constructor
     * @param datosDireccion
     */
    public Direccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.numero = datosDireccion.numero();
        this.colonia = datosDireccion.colonia();
        this.codigoPostal = datosDireccion.codigoPostal();
        this.ciudad = datosDireccion.ciudad();
        this.estado = datosDireccion.estado();
    }

    /**
     * Método para Actualizar los datos de la Dirección
     * @param datos
     */
    public void actualizarDireccion(DatosDireccion datos) {
        if(datos.calle() != null){ this.calle = datos.calle(); }
        if(datos.numero() != null){ this.numero = datos.numero(); }
        if(datos.colonia() != null){ this.colonia = datos.colonia(); }
        if(datos.codigoPostal() != null){ this.codigoPostal = datos.codigoPostal(); }
        if(datos.ciudad() != null){ this.ciudad = datos.ciudad(); }
        if(datos.estado() != null){ this.estado = datos.estado(); }
    }

}
