package com.ceiba.reserva.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReservaTest {

    @Test
    void crearReservaSinError() {
        LocalDateTime fechaEntradaEsperada = LocalDateTime.now().plusDays(1).withHour(15).withMinute(00).withSecond(00).withNano(0);
        LocalDateTime fechaSalidaEsperada = LocalDateTime.now().plusDays(2).withHour(12).withMinute(00).withSecond(00).withNano(0);

        Reserva reserva = new ReservaTestDataBuilder().build();

        assertEquals("prueba", reserva.getNombre());
        assertEquals(fechaEntradaEsperada, reserva.getFechaEntrada());
        assertEquals("prueba", reserva.getNumeroHabitacion());
        assertEquals(fechaSalidaEsperada, reserva.getFechaSalida());
        assertEquals(LocalDate.now(), reserva.getFechaRegistro());
        assertEquals(0.0, reserva.getCostoTotal());
        assertNull(reserva.getEstadoReserva());
    }

    @Test
    void crearReservaActualizarSinError() {

        Reserva reserva = new ReservaTestDataBuilder().buildActualizar();

        assertEquals(1, reserva.getId());
        assertEquals("prueba", reserva.getNombre());
        assertEquals("reservado", reserva.getEstadoReserva());
    }

    @Test
    void crearReservaConErrorNombre() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombre(null);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar el nombre");
    }

    @Test
    void crearReservaConErrorNombreVacio() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombre("");

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar el nombre");
    }

    @Test
    void crearReservaConErrorFechaEntreda() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaEntrada(null);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de entrada");
    }

    @Test
    void crearReservaConErrorNumeroHabitacion() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNumeroHabitacion(null);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar el numero de habitacion");
    }

    @Test
    void crearReservaConErrorNumeroHabitacionVacio() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNumeroHabitacion("");

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar el numero de habitacion");
    }

    @Test
    void crearReservaConErrorFechaSalida() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaSalida(null);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de salida");
    }


    @Test
    void crearReservaConErrorFechaEntradaMenorAHoy() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaEntrada(LocalDateTime.now().minusDays(1));

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.build();
        }, ExcepcionValorInvalido.class, "La fecha de entrada debe ser mayor o igual a la actual");
    }

    @Test
    void crearReservaConErrorFechaEntradaMayorAFechaSalidaFechasDiferentes() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder()
                .conFechaEntrada(LocalDateTime.now().plusDays(4))
                .conFechaSalida(LocalDateTime.now());

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.build();
        }, ExcepcionValorInvalido.class, "La fecha de salida debe ser mayor a la fecha de entrada");
    }

    @Test
    void crearReservaConErrorFechaEntradaMayorAFechaSalidaFechasIguales() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder()
                .conFechaEntrada(LocalDateTime.now().plusDays(1))
                .conFechaSalida(LocalDateTime.now().plusDays(1));

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.build();
        }, ExcepcionValorInvalido.class, "La fecha de salida debe ser mayor a la fecha de entrada");
    }

    @Test
    void crearReservaConIdNegativo() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conId(-1);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildActualizar();
        }, ExcepcionValorInvalido.class, "El numero de reerva debe ser positivo");
    }

    @Test
    void crearReservaConEstadoVacio() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conEstado("");

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildActualizar();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar un estado");
    }

    @Test
    void crearReservaConEstadoNulo() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conEstado(null);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildActualizar();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar un estado");
    }

    @Test
    void crearReservaConEstadoNoValido() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conEstado("prueba");

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildActualizar();
        }, ExcepcionValorInvalido.class, "Se debe ingresar un estado valido");
    }

    @Test
    void crearReservaActualizarConErrorNombre() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombre(null);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildActualizar();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar el nombre");
    }

    @Test
    void crearReservaActualizarConErrorNombreVacio() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombre("");

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildActualizar();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar el nombre");
    }

    @Test
    void crearReservaCambiarEstadoConIdNegativo() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conId(-1);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildConTodosLosDatos();
        }, ExcepcionValorInvalido.class, "El numero de reerva debe ser positivo");
    }

    @Test
    void crearReservaCambiarEstadoConErrorNombre() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombre(null);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildConTodosLosDatos();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar el nombre");
    }

    @Test
    void crearReservaCambiarEstadoConErrorNombreVacio() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombre("");

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildConTodosLosDatos();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar el nombre");
    }

    @Test
    void crearReservaCambiarEstadoConEstadoVacio() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conEstado("");

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildConTodosLosDatos();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar un estado");
    }

    @Test
    void crearReservaCambiarEstadoConEstadoNulo() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conEstado(null);

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildConTodosLosDatos();
        }, ExcepcionValorObligatorio.class, "Se debe ingresar un estado");
    }

    @Test
    void crearReservaCambiarEstadoConEstadoNoValido() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conEstado("prueba");

        BasePrueba.assertThrows(() -> {
            reservaTestDataBuilder.buildConTodosLosDatos();
        }, ExcepcionValorInvalido.class, "Se debe ingresar un estado valido");
    }


}