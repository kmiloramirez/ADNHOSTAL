package com.ceiba.reserva.cobro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReglaCobroSabadoYDomingoTest {


    private ReglaCobroSabadoYDomingo reglaCobroSabadoYDomingo;

    @BeforeEach
    void setUp() {
        reglaCobroSabadoYDomingo = new ReglaCobroSabadoYDomingo();
    }

    @Test
    void cobrarParaUnaReservaDeLunesADomingo() {
        LocalDate fechaEntrada = LocalDate.of(2021, 12, 20);
        LocalDate fechaSalida = LocalDate.of(2021, 12, 27);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroSabadoYDomingo.cobrar(fechaEntrada, fechaSalida, precioHabitacion);

        assertEquals(230.0, resultado);

    }

    @Test
    void cobrarParaUnaReservaDeLunesAMartes() {
        LocalDate fechaEntrada = LocalDate.of(2021, 12, 20);
        LocalDate fechaSalida = LocalDate.of(2021, 12, 28);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroSabadoYDomingo.cobrar(fechaEntrada, fechaSalida, precioHabitacion);

        assertEquals(230.0, resultado);

    }

    @Test
    void cobrarParaUnaReservaDeSabadoADomingo() {
        LocalDate fechaEntrada = LocalDate.of(2021, 12, 25);
        LocalDate fechaSalida = LocalDate.of(2021, 12, 26);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroSabadoYDomingo.cobrar(fechaEntrada, fechaSalida, precioHabitacion);

        assertEquals(115.0, resultado);

    }

    @Test
    void cobrarParaUnaReservaDeLunesAViernes() {
        LocalDate fechaEntrada = LocalDate.of(2021, 12, 20);
        LocalDate fechaSalida = LocalDate.of(2021, 12, 24);
        double precioHabitacion = 230.0;

        double resultado = reglaCobroSabadoYDomingo.cobrar(fechaEntrada, fechaSalida, precioHabitacion);

        assertEquals(0.0, resultado);

    }
}