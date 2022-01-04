package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCobrarReservaTest {

    private ServicioCobrarDolaresReserva servicioCobrarReserva;

    @BeforeEach
    void setUp() {
        servicioCobrarReserva = new ServicioCobrarDolaresReserva();
    }

    @Test
    void ejecutar() {
        double costoTotal = 4000.0;
        double valorTrm = 4000.0;
        DtoReservaCobro reservaCobro = new ReservaTestDataBuilder().conCostoTotal(costoTotal).conTrm(valorTrm)
                .buildReservaCobrar();

        servicioCobrarReserva.ejecutar(reservaCobro);

        assertEquals(costoTotal, reservaCobro.getCostoTotalPesos());
        assertEquals(1, reservaCobro.getCostoTotalDolares());

    }
}