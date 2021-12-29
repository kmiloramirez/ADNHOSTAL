package com.ceiba.reserva.cobro;

import java.time.LocalDate;

public class ReglaCobroDescuentoPorDias implements ReglaCobro {
    private static final double DESCUENTO = 0.10;
    private static final int DIAS_MINIMO_DESCUENTO = 5;
    private static final double CERO = 0.0;
    private static final double MENOS_UNO = -1.0;

    @Override
    public double cobrar(LocalDate fechaEntrada, LocalDate fechaSalida, double precioHabitacion) {
        int diasDeReserva = fechaSalida.compareTo(fechaEntrada);
        if (diasDeReserva >= DIAS_MINIMO_DESCUENTO) {
            double precioTotal = precioHabitacion * diasDeReserva;
            return (MENOS_UNO) * (precioTotal * DESCUENTO) ;
        }
        return CERO;
    }
}
