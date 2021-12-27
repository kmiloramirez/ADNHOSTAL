package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioActualizarReserva {

    private static final String LA_RESERVA_NO_EXISTE = "La reservar no existe";


    private final RepositorioReserva repositorioReserva;

    public ServicioActualizarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Reserva reserva){
        validarExisteReserva(reserva.getNumeroReserva());
        repositorioReserva.actualizar(reserva);
    }

    private void validarExisteReserva(int numeroReserva) {
        if (!repositorioReserva.existeReserva(numeroReserva)) {
            throw new ExcepcionSinDatos(LA_RESERVA_NO_EXISTE);
        }
    }

}
