package com.ceiba.reserva.cobro;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReglaCobroSabadoYDomingo implements ReglaCobro {
    private static final double SOBRECOSTO = 0.15;
    private static final DayOfWeek SABADO = DayOfWeek.SATURDAY;
    private static final DayOfWeek DOMINGO = DayOfWeek.SUNDAY;


    @Override
    public double cobrar(LocalDate fechaEntrada, LocalDate fechaSalida, double precioHabitacion) {
        int diasDeDescuento = calcularDiasSabadoADomingoEnReserva(fechaEntrada, fechaSalida);
        double precioTotal = precioHabitacion * diasDeDescuento;
        return precioTotal + (precioTotal * SOBRECOSTO);

    }

    private int calcularDiasSabadoADomingoEnReserva(LocalDate fechaEntrada, LocalDate fechaSalida) {
        int diasSabadoADomingo = 0;
        int diasDeReserva = fechaSalida.compareTo(fechaEntrada);
        for (int diaDeReserva = 0; diaDeReserva < diasDeReserva; diaDeReserva++) {
            LocalDate diaReserva = fechaEntrada.plusDays(diaDeReserva);
            if (diaReserva.getDayOfWeek() == SABADO || diaReserva.getDayOfWeek() == DOMINGO) {
                diasSabadoADomingo++;
            }
        }
        return diasSabadoADomingo;
    }
}
