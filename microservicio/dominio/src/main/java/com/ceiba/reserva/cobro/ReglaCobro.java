package com.ceiba.reserva.cobro;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;

public interface ReglaCobro {

    double cobrar(LocalDate fechaEntrada ,LocalDate fechaSalida, double precioHabitacion);
}
