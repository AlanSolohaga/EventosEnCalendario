package com.example.pruebacalendario;

import java.util.Date;

public class FechaSalon {
    private Date fecha;
    EnumEstadoFecha estado;

    public FechaSalon(Date fecha,EnumEstadoFecha estado) {
        this.fecha = fecha;
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EnumEstadoFecha getEstado(){
        return estado;
    }

    public void setEstado(EnumEstadoFecha estado) {
        this.estado = estado;
    }
}
