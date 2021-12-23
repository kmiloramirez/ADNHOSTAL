package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.ceiba.reserva.modelo.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ServicioCrearReservaTest {

    private ServicioCrearReserva servicioCrearReserva;
    private RepositorioReserva repositorioReserva;
    private ServicioCalcularPrecioReserva servicioCalcularPrecioReserva;

    @BeforeEach
    void setUp() {
        repositorioReserva = Mockito.mock(RepositorioReserva.class);
        servicioCalcularPrecioReserva = Mockito.mock(ServicioCalcularPrecioReserva.class);
        servicioCrearReserva = new ServicioCrearReserva(repositorioReserva, servicioCalcularPrecioReserva);
    }

    @Test
    void ejecutar(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.doReturn(1L).when(repositorioReserva).crear(reserva);
        Mockito.doReturn(true).when(repositorioReserva).existeHabitacion(reserva.getNumeroHabitacion());
        Mockito.doReturn(true).when(repositorioReserva).disponibilidadHabitacion(reserva.getNumeroHabitacion(),
                reserva.getFechaEntrada(),reserva.getFechaSalida());
        Mockito.doReturn(reserva).when(servicioCalcularPrecioReserva).ejecutar(reserva);

        Long idReserva = servicioCrearReserva.ejecutar(reserva);

        assertEquals(EstadoReserva.RESEVADO.getEstado(),reserva.getEstadoReserva());
        assertEquals(1l,idReserva);
    }

    @Test
    void ejecutarConHabitacionNoExiste(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.doReturn(false).when(repositorioReserva).existeHabitacion(reserva.getNumeroHabitacion());


        BasePrueba.assertThrows(() -> {
            servicioCrearReserva.ejecutar(reserva);
        }, ExcepcionSinDatos.class,"La habitacion en la que intenta reservar no existe");
    }

    @Test
    void ejecutarConHabitacionNoDisponible(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.doReturn(true).when(repositorioReserva).existeHabitacion(reserva.getNumeroHabitacion());
        Mockito.doReturn(false).when(repositorioReserva).disponibilidadHabitacion(reserva.getNumeroHabitacion(),
                reserva.getFechaEntrada(),reserva.getFechaSalida());

        BasePrueba.assertThrows(() -> {
            servicioCrearReserva.ejecutar(reserva);
        }, ExcepcionDuplicidad.class,"La habitacion no esta disponible en esas fechas");
    }
}