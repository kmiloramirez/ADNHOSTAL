package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;

public interface RepositorioReserva {


    /**
     * Permite crear una reserva
     *
     * @param reserva
     * @return el id generado
     */
    Long crear(Reserva reserva);

    /**
     * Permite validar si existe una habitacion para realizar la reserva
     *
     * @param numeroHabitacion
     * @return si existe la habitacion
     */
    boolean existeHabitacion(String numeroHabitacion);

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
    boolean disponibilidadHabitacion(String numeroHabitacion, LocalDate fechaEntrada, LocalDate fechaSalida);

    /**
     * Permite consultar el precio de una habitacion reservada
     *
     * @param numeroHabitacion
     * @return si existe la habitacion
     */
    double precioHabitacion(String numeroHabitacion);
}
