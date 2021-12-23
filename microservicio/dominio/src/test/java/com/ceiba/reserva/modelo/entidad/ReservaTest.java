package com.ceiba.reserva.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    @Test
    void crearReservaSinError(){
        Reserva reserva = new ReservaTestDataBuilder().build();

        assertEquals("prueba",reserva.getNombre());
        assertEquals(LocalDate.now().plusDays(1),reserva.getFechaEntrada());
        assertEquals("prueba",reserva.getNumeroHabitacion());
        assertEquals(LocalDate.now().plusDays(2),reserva.getFechaSalida());
        assertEquals(LocalDate.now(),reserva.getFechaRegistro());
        assertEquals(0.0,reserva.getCostoTotal());
        assertEquals("",reserva.getEstadoReserva());
    }

    @Test
    void crearReservaConErrorNombre(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombre(null);

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"Se debe ingresar el nombre");
    }

    @Test
    void crearReservaConErrorNombreVacio(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombre("");

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"Se debe ingresar el nombre");
    }

    @Test
    void crearReservaConErrorFechaEntreda(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaEntrada(null);

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"Se debe ingresar la fecha de entrada");
    }

    @Test
    void crearReservaConErrorNumeroHabitacion(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNumeroHabitacion(null);

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"Se debe ingresar el numero de habitacion");
    }

    @Test
    void crearReservaConErrorNumeroHabitacionVacio(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNumeroHabitacion("");

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"Se debe ingresar el numero de habitacion");
    }

    @Test
    void crearReservaConErrorFechaSalida(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaSalida(null);

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"Se debe ingresar la fecha de salida");
    }

    @Test
    void crearReservaConErrorFechaRegistro(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaRegistro(null);

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"Se debe ingresar la fecha de registro");
    }

    @Test
    void crearReservaConErrorFechaEntradaMenorAHoy(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaEntrada(LocalDate.now().minusDays(1));

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorInvalido.class,"La fecha de entrada debe ser mayor o igual a la actual");
    }

    @Test
    void crearReservaConErrorFechaEntradaMayorAFechaSalidaFechasDiferentes(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder()
                .conFechaEntrada(LocalDate.now().plusDays(4))
                .conFechaSalida(LocalDate.now());

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorInvalido.class,"La fecha de salida debe ser mayor a la fecha de entrada");
    }

    @Test
    void crearReservaConErrorFechaEntradaMayorAFechaSalidaFechasIguales(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder()
                .conFechaEntrada(LocalDate.now().plusDays(1))
                .conFechaSalida(LocalDate.now().plusDays(1));

        BasePrueba.assertThrows(()->{
            reservaTestDataBuilder.build();
        }, ExcepcionValorInvalido.class,"La fecha de salida debe ser mayor a la fecha de entrada");
    }

}