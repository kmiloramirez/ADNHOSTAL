package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioActualizarReservaTest {

    private ServicioActualizarReserva servicioActualizarReserva;
    private RepositorioReserva repositorioReserva;

    @BeforeEach
    void setUp() {
        repositorioReserva = Mockito.mock(RepositorioReserva.class);
        servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
    }

    @Test
    void ejecutar() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.doReturn(true).when(repositorioReserva).existeReserva(reserva.getNumeroReserva());

        servicioActualizarReserva.ejecutar(reserva);

        Mockito.verify(repositorioReserva).existeReserva(reserva.getNumeroReserva());
        Mockito.verify(repositorioReserva).actualizar(reserva);
    }

    @Test
    void ejecutarReservaNoExiste() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.doReturn(false).when(repositorioReserva).existeReserva(reserva.getNumeroReserva());

        BasePrueba.assertThrows(() -> servicioActualizarReserva.ejecutar(reserva),
                ExcepcionSinDatos.class, "La reservar no existe");
    }
}