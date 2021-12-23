package com.ceiba.reserva.modelo.enumerador;

public enum EstadoReserva {

    RESEVADO("reservado"), ACTIVA("activa"), TERMINADA("terminada");

    private final String estado;

    EstadoReserva(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
