package com.alura.forohub.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String colonia;
    private String codigo_postal;
    private String ciudad;
    private String estado;

    public Direccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.numero = datosDireccion.numero();
        this.colonia = datosDireccion.colonia();
        this.codigo_postal = datosDireccion.codigo_postal();
        this.ciudad = datosDireccion.ciudad();
        this.estado = datosDireccion.estado();
    }

    public void actualizarDireccion(DatosDireccion datos) {
        if(datos.calle() != null){ this.calle = datos.calle(); }
        if(datos.numero() != null){ this.numero = datos.numero(); }
        if(datos.colonia() != null){ this.colonia = datos.colonia(); }
        if(datos.codigo_postal() != null){ this.codigo_postal = datos.codigo_postal(); }
        if(datos.ciudad() != null){ this.ciudad = datos.ciudad(); }
        if(datos.estado() != null){ this.estado = datos.estado(); }
    }

}
