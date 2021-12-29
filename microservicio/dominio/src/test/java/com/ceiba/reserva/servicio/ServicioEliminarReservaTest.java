package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionEstado;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioEliminarReservaTest {

    private ServicioEliminarReserva servicioEliminarReserva;
    private RepositorioReserva repositorioReserva;
    private DaoReserva daoReserva;

    @BeforeEach
    void setUp() {
        repositorioReserva = Mockito.mock(RepositorioReserva.class);
        daoReserva = Mockito.mock(DaoReserva.class);
        servicioEliminarReserva = new ServicioEliminarReserva(repositorioReserva, daoReserva);

    }

    @Test
    void ejecutar() {
        int numeroReserva = 1;
        Mockito.doReturn(EstadoReserva.RESEVADO.getEstado()).when(daoReserva).obtenerEstadoReservaReserva(numeroReserva);

        servicioEliminarReserva.ejecutar(numeroReserva);

        Mockito.verify(daoReserva).obtenerEstadoReservaReserva(numeroReserva);
        Mockito.verify(repositorioReserva).eliminar(numeroReserva);
    }

    @Test
    void ejecutarConError() {
        int numeroReserva = 1;
        Mockito.doReturn(EstadoReserva.ACTIVA.getEstado()).when(daoReserva).obtenerEstadoReservaReserva(numeroReserva);

        BasePrueba.assertThrows(() -> servicioEliminarReserva.ejecutar(numeroReserva),
                ExcepcionEstado.class, "La reserva no se puede borrar por que su estado es: " + EstadoReserva.ACTIVA.getEstado());
    }
}