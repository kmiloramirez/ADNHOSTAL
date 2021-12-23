package com.ceiba.reserva.servicio;

import com.ceiba.reserva.cobro.ReglaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
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
    private RepositorioReserva repositorioReserva;

    @BeforeEach
    void setUp() {
        reglasCobros = new ArrayList<>();
        reglaCobro = Mockito.mock(ReglaCobro.class);
        reglasCobros.add(reglaCobro);
        repositorioReserva = Mockito.mock(RepositorioReserva.class);
        servicioCalcularPrecioReserva = new ServicioCalcularPrecioReserva(reglasCobros, repositorioReserva);
    }

    @Test
    void ejecutar() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        double precioHabitacion = 100.0;
        double precioTotal = 200.0;
        Mockito.doReturn(precioHabitacion).when(repositorioReserva).precioHabitacion(reserva.getNumeroHabitacion());
        Mockito.doReturn(precioTotal).when(reglaCobro).cobrar(reserva.getFechaEntrada(), reserva.getFechaSalida(), precioHabitacion);

        Reserva resultado = servicioCalcularPrecioReserva.ejecutar(reserva);

        assertEquals(precioTotal, reserva.getCostoTotal());
    }
}