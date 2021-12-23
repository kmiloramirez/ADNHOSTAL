package com.ceiba.reserva.cobro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReglaCobroOrdinariaTest {
    private ReglaCobroOrdinaria reglaCobroOrdinaria;

    @BeforeEach
    void setUp() {
        reglaCobroOrdinaria = new ReglaCobroOrdinaria();
    }

    @Test
    void cobrarParaUnaReservaDeLunesADOmingo() {
        LocalDate fechaEntrada = LocalDate.of(2021,12,20);
        LocalDate fechaSalida = LocalDate.of(2021,12,27);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroOrdinaria.cobrar(fechaEntrada,fechaSalida,precioHabitacion);

        assertEquals(200.0,resultado);

    }
    @Test
    void cobrarParaUnaReservaDeLunesAMartes() {
        LocalDate fechaEntrada = LocalDate.of(2021,12,20);
        LocalDate fechaSalida = LocalDate.of(2021,12,28);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroOrdinaria.cobrar(fechaEntrada,fechaSalida,precioHabitacion);

        assertEquals(200.0,resultado);

    }
    @Test
    void cobrarParaUnaReservaDeJuevesAViernes() {
        LocalDate fechaEntrada = LocalDate.of(2021,12,23);
        LocalDate fechaSalida = LocalDate.of(2021,12,24);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroOrdinaria.cobrar(fechaEntrada,fechaSalida,precioHabitacion);

        assertEquals(100.0,resultado);

    }

    @Test
    void cobrarParaUnaReservaDeSabadoAMiercoles() {
        LocalDate fechaEntrada = LocalDate.of(2021,12,25);
        LocalDate fechaSalida = LocalDate.of(2021,12,29);
        double precioHabitacion = 100.0;

        double resultado = reglaCobroOrdinaria.cobrar(fechaEntrada,fechaSalida,precioHabitacion);

        assertEquals(0.0,resultado);

    }
}