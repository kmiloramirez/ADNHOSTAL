package com.ceiba.reserva.cobro;

import java.time.LocalDate;

public class ReglaCobroDescuentoPorDias implements ReglaCobro{
    private final static double DESCUENTO = 0.10;
    private final static int DIAS_MINIMO_DESCUENTO= 5;
    private final static double CERO = 0.0;
    private final static double MENOS_UNO = -1.0;

    @Override
    public double cobrar(LocalDate fechaEntrada, LocalDate fechaSalida, double precioHabitacion) {
        int diasDeReserva = fechaSalida.compareTo(fechaEntrada);
        if(diasDeReserva>= DIAS_MINIMO_DESCUENTO){
            double precioTotal = precioHabitacion * diasDeReserva;
            return (precioTotal * DESCUENTO) * (MENOS_UNO);
        }
        return CERO;
    }
}
