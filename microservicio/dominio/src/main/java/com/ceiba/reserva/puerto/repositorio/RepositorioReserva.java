package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public interface RepositorioReserva {


    /**
     * Permite crear una reserva
     *
     * @param reserva
     * @return el id generado
     */
    Long crear(Reserva reserva);

    /**
     * Permite validar disponibilidad de la habitacion para realizar la reserva
     * si se encuenta una reserva ya existente con fecha de salida menor a la de entrada
     * se debe validar la fecha de salida de sea menor a la entrada de la nueva reserva
     *
     * @param numeroHabitacion
     * @param fechaEntrada
     * @param fechaSalida
     * @return si existe la habitacion
     */
    boolean disponibilidadHabitacion(String numeroHabitacion, LocalDateTime fechaEntrada, LocalDateTime fechaSalida);

    /**
     * Permite actualizar una reserva
     *
     * @param reserva
     */
    void actualizar(Reserva reserva);

    /**
     * Permite validar si existe una reserva
     *
     * @param numeroReserva
     * @return el id generado
     */
    boolean existeReserva(int numeroReserva);

    /**
     * Permite eliminar una reserva
     *
     * @param numeroReserva
     *
     */
    void eliminar(int numeroReserva);

}
