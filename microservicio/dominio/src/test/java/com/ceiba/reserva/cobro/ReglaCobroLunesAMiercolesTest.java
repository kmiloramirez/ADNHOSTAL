package com.ceiba.reserva.cobro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReglaCobroLunesAMiercolesTest {

    private ReglaCobroLunesAMiercoles reglaCobroLunesAMiercoles;

    @BeforeEach
    void setUp() {
        reglaCobroLunesAMiercoles = new ReglaCobroLunesAMiercoles();
    }

    @Test
    void cobrarParaUnaReservaDeLunesADOmingo() {
        LocalDate fechaEntrada = LocalDate.of(2021, 12, 20);
        LocalDate fechaSalida = LocalDate.of(2021, 12, 27);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroLunesAMiercoles.cobrar(fechaEntrada, fechaSalida, precioHabitacion);

        assertEquals(240.0, resultado);

    }

    @Test
    void cobrarParaUnaReservaDeLunesAMartes() {
        LocalDate fechaEntrada = LocalDate.of(2021, 12, 20);
        LocalDate fechaSalida = LocalDate.of(2021, 12, 21);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroLunesAMiercoles.cobrar(fechaEntrada, fechaSalida, precioHabitacion);

        assertEquals(80.0, resultado);

    }

    @Test
    void cobrarParaUnaReservaDeJuevesAJueves() {
        LocalDate fechaEntrada = LocalDate.of(2021, 12, 23);
        LocalDate fechaSalida = LocalDate.of(2021, 12, 30);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroLunesAMiercoles.cobrar(fechaEntrada, fechaSalida, precioHabitacion);

        assertEquals(240.0, resultado);

    }

    @Test
    void cobrarParaUnaReservaDeJuevesADomingo() {
        LocalDate fechaEntrada = LocalDate.of(2021, 12, 23);
        LocalDate fechaSalida = LocalDate.of(2021, 12, 26);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroLunesAMiercoles.cobrar(fechaEntrada, fechaSalida, precioHabitacion);

        assertEquals(0.0, resultado);

    }
}