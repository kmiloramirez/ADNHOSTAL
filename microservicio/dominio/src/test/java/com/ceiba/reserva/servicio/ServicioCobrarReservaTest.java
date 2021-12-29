package com.ceiba.reserva.servicio;

import com.ceiba.infraestructura.excepcion.ExcepcionTrm;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.trm.servicio.ServicioConsultarTrm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ServicioCobrarReservaTest {

    private ServicioCobrarReserva servicioCobrarReserva;
    private ServicioConsultarTrm servicioConsultarTrm;

    @BeforeEach
    void setUp() {
        servicioConsultarTrm = Mockito.mock(ServicioConsultarTrm.class);
        servicioCobrarReserva = new ServicioCobrarReserva(servicioConsultarTrm);
    }

    @Test
    void ejecutarConErrorConsultaTrm() {
        double costoTotal = 4000.0;
        String errorTrm = "Error consultando trm";
        Reserva reserva = new ReservaTestDataBuilder().conCostoTotal(costoTotal).buildConTodosLosDatos();
        DtoReserva reservaConsultada = new DtoReserva(reserva.getId(), reserva.getNombre(), reserva.getFechaEntrada(),
                reserva.getNumeroHabitacion(), reserva.getFechaSalida(), reserva.getFechaRegistro(), reserva.getCostoTotal(),
                reserva.getEstadoReserva());
        Mockito.doThrow(new ExcepcionTrm(errorTrm, new RuntimeException(errorTrm))).when(servicioConsultarTrm)
                .ejecutar(reservaConsultada.getFechaSalida().toLocalDate());

        DtoReservaCobro resultado = servicioCobrarReserva.ejecutar(reserva);

        assertEquals(reservaConsultada.getNumeroReserva(), resultado.getId());
        assertEquals(reservaConsultada.getNumeroHabitacion(), resultado.getNumeroHabitacion());
        assertEquals(reservaConsultada.getFechaSalida(), resultado.getFechaSalida());
        assertEquals(costoTotal, resultado.getCostoTotalPesos());
        assertEquals(costoTotal, resultado.getCostoTotalDolares());
        assertEquals(errorTrm, resultado.getErroresProcesamiento());

    }

    @Test
    void ejecutarSinError() {
        double costoTotal = 4000.0;
        double valorTrm = 4000.0;
        Reserva reserva = new ReservaTestDataBuilder().conCostoTotal(costoTotal).buildConTodosLosDatos();
        DtoReserva reservaConsultada = new DtoReserva(reserva.getId(), reserva.getNombre(), reserva.getFechaEntrada(),
                reserva.getNumeroHabitacion(), reserva.getFechaSalida(), reserva.getFechaRegistro(), reserva.getCostoTotal(),
                reserva.getEstadoReserva());
        Mockito.doReturn(valorTrm).when(servicioConsultarTrm).ejecutar(reservaConsultada.getFechaSalida().toLocalDate());

        DtoReservaCobro resultado = servicioCobrarReserva.ejecutar(reserva);

        assertEquals(reservaConsultada.getNumeroReserva(), resultado.getId());
        assertEquals(reservaConsultada.getNumeroHabitacion(), resultado.getNumeroHabitacion());
        assertEquals(reservaConsultada.getFechaSalida(), resultado.getFechaSalida());
        assertEquals(costoTotal, resultado.getCostoTotalPesos());
        assertEquals(1, resultado.getCostoTotalDolares());
        assertNull(resultado.getErroresProcesamiento());

    }
}