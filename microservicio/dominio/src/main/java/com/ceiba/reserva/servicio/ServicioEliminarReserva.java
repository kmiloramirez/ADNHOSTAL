package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionEstado;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioEliminarReserva {

    private static final String RESERVA_NO_SE_PUEDE_BORRAR = "La reserva no se puede borrar por que su estado es: %s";

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;

    public ServicioEliminarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
    }

    public void ejecutar(int numeroReserva){
        validarEstadoReserva(numeroReserva);
        repositorioReserva.eliminar(numeroReserva);
    }

    private void validarEstadoReserva(int numeroReserva) {
        String estadoConsultado = daoReserva.obtenerEstadoReservaReserva(numeroReserva);
        if(!EstadoReserva.RESEVADO.getEstado().equals(estadoConsultado)){
            throw new ExcepcionEstado(String.format(RESERVA_NO_SE_PUEDE_BORRAR,estadoConsultado));
        }
    }
}
