package com.ceiba.reserva.modelo.enumerador;

import java.util.ArrayList;
import java.util.List;

public enum EstadoReserva {

    RESEVADO("reservado"), ACTIVA("activa"), TERMINADA("terminada");

    private final String estado;

    EstadoReserva(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public static List<String> getEstados() {
        List<String> estados = new ArrayList<>();
        estados.add(RESEVADO.getEstado());
        estados.add(ACTIVA.getEstado());
        estados.add(TERMINADA.getEstado());
        return estados;
    }
}
