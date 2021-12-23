package com.ceiba.reserva.cobro;

import java.time.LocalDate;

public interface ReglaCobro {

    double cobrar(LocalDate fechaEntrada, LocalDate fechaSalida, double precioHabitacion);
}
