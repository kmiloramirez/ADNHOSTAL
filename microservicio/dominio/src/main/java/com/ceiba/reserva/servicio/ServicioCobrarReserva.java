package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

public class ServicioCobrarReserva {

    private static final String LA_RESERVA_NO_EXISTE = "La reservar no existe";

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;

    public ServicioCobrarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
    }

    public DtoReservaCobro ejecutar(int numeroReserva){
        validarExisteReserva(numeroReserva);
        DtoReserva reservaConsultada = daoReserva.obtenerReserva(numeroReserva);
        Reserva reserva = new Reserva(reservaConsultada.getNumeroReserva(),reservaConsultada.getNombre(),
                EstadoReserva.TERMINADA.getEstado());
        repositorioReserva.actualizar(reserva);
        double valorTrm = obtenerValorTrm();
        return  new DtoReservaCobro(numeroReserva,reservaConsultada.getNumeroHabitacion(),
                reservaConsultada.getCostoTotal(),reservaConsultada.getCostoTotal()/valorTrm);
    }

    private double obtenerValorTrm() {
        return 4000;
    }

    private void validarExisteReserva(int numeroReserva) {
        if (!repositorioReserva.existeReserva(numeroReserva)) {
            throw new ExcepcionSinDatos(LA_RESERVA_NO_EXISTE);
        }
    }
}
