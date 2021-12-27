package com.ceiba.reserva.puerto.dao;

import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.util.List;

public interface DaoReserva {
    /**
     * Permite obtener las reservas
     * @return reservas
     */
    DtoReserva obtenerReserva(int numeroReserva);

    /**
     * Permite obtener las reservas
     * @return reservas
     */
    List<DtoReserva> listar();
    /**
     * Permite obtener una reserva por estado
     *
     * @param estado
     * @return reserva
     */
    List<DtoReserva> consultarReservaEstado(String estado);
}
