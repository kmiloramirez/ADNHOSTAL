package com.ceiba.reserva.cobro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReglaCobroDescuentoTest {

    private ReglaCobroDescuentoPorDias reglaCobroDescuento;

    @BeforeEach
    void setUp() {
        reglaCobroDescuento = new ReglaCobroDescuentoPorDias();
    }

    @Test
    void cobrarSinDescuento() {
        LocalDate fechaEntrada = LocalDate.of(2021,12,20);
        LocalDate fechaSalida = LocalDate.of(2021,12,23);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroDescuento.cobrar(fechaEntrada,fechaSalida,precioHabitacion);

        assertEquals(0.0,resultado);
    }

    @Test
    void cobrarConDescuento() {
        LocalDate fechaEntrada = LocalDate.of(2021,12,20);
        LocalDate fechaSalida = LocalDate.of(2021,12,25);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroDescuento.cobrar(fechaEntrada,fechaSalida,precioHabitacion);

        assertEquals(-50.0,resultado);
    }
}