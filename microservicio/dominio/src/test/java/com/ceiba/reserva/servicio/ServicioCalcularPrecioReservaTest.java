package com.ceiba.reserva.servicio;

import com.ceiba.habitacion.puerto.dao.DaoHabitacion;
import com.ceiba.reserva.cobro.ReglaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCalcularPrecioReservaTest {

    private ServicioCalcularPrecioReserva servicioCalcularPrecioReserva;
    private List<ReglaCobro> reglasCobros;
    private ReglaCobro reglaCobro;
    private DaoHabitacion daoHabitacion;

    @BeforeEach
    void setUp() {
        reglasCobros = new ArrayList<>();
        reglaCobro = Mockito.mock(ReglaCobro.class);
        reglasCobros.add(reglaCobro);
        daoHabitacion = Mockito.mock(DaoHabitacion.class);
        servicioCalcularPrecioReserva = new ServicioCalcularPrecioReserva(reglasCobros, daoHabitacion);
    }

    @Test
    void ejecutar() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        double precioHabitacion = 100.0;
        double precioTotal = 200.0;
        Mockito.doReturn(precioHabitacion).when(daoHabitacion).obtenerPrecioHabitacion(reserva.getNumeroHabitacion());
        Mockito.doReturn(precioTotal).when(reglaCobro).cobrar(reserva.getFechaEntrada().toLocalDate(), reserva.getFechaSalida().toLocalDate(), precioHabitacion);

        Reserva resultado = servicioCalcularPrecioReserva.ejecutar(reserva);

        assertEquals(precioTotal, reserva.getCostoTotal());
    }
}