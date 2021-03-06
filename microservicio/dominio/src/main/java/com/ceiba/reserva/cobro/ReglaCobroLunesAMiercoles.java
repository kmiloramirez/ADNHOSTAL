package com.ceiba.reserva.cobro;


import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReglaCobroLunesAMiercoles implements ReglaCobro {
    private static final double DESCUENTO = 0.20;
    private static final DayOfWeek LUNES = DayOfWeek.MONDAY;
    private static final DayOfWeek MARTES = DayOfWeek.TUESDAY;
    private static final DayOfWeek MIERCOLES = DayOfWeek.WEDNESDAY;


    @Override
    public double cobrar(LocalDate fechaEntrada, LocalDate fechaSalida, double precioHabitacion) {
        int diasDeDescuento = calcularDiasLunesAMiercolesEnReserva(fechaEntrada, fechaSalida);
        double precioTotal = precioHabitacion * diasDeDescuento;
        return precioTotal - (precioTotal * DESCUENTO);

    }

    private int calcularDiasLunesAMiercolesEnReserva(LocalDate fechaEntrada, LocalDate fechaSalida) {
        int diasLunesAMiercoles = 0;
        int diasDeReserva = fechaSalida.compareTo(fechaEntrada);
        for (int diaDeReserva = 0; diaDeReserva < diasDeReserva; diaDeReserva++) {
            LocalDate diaReserva = fechaEntrada.plusDays(diaDeReserva);
            if (diaReserva.getDayOfWeek() == LUNES || diaReserva.getDayOfWeek() == MARTES
                    || diaReserva.getDayOfWeek() == MIERCOLES) {
                diasLunesAMiercoles++;
            }
        }
        return diasLunesAMiercoles;
    }
}
