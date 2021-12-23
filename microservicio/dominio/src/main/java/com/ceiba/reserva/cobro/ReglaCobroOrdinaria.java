package com.ceiba.reserva.cobro;


import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReglaCobroOrdinaria implements ReglaCobro {
    private static final DayOfWeek JUEVES = DayOfWeek.THURSDAY;
    private static final DayOfWeek VIERNES = DayOfWeek.FRIDAY;

    @Override
    public double cobrar(LocalDate fechaEntrada, LocalDate fechaSalida, double precioHabitacion) {
        int diasOrdinarios = calcularDiasOrdinariosEnReserva(fechaEntrada, fechaSalida);
        return precioHabitacion * diasOrdinarios;
    }

    private int calcularDiasOrdinariosEnReserva(LocalDate fechaEntrada, LocalDate fechaSalida) {
        int diasOrdinario = 0;
        int diasDeReserva = fechaSalida.compareTo(fechaEntrada);
        for (int diaDeReserva = 0; diaDeReserva < diasDeReserva; diaDeReserva++) {
            LocalDate diaReserva = fechaEntrada.plusDays(diaDeReserva);
            if (diaReserva.getDayOfWeek() == JUEVES || diaReserva.getDayOfWeek() == VIERNES) {
                diasOrdinario++;
            }
        }
        return diasOrdinario;
    }
}
