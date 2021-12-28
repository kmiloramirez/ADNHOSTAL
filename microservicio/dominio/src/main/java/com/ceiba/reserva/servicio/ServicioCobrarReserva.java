package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.infraestructura.excepcion.ExcepcionTrm;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.trm.servicio.ServicioConsultarTrm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static com.ceiba.dominio.formato.FormatoDouble.darFormatoDosDecimales;

public class ServicioCobrarReserva {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicioCobrarReserva.class);
    private static final String LA_RESERVA_NO_EXISTE = "La reservar no existe";

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;
    private final ServicioConsultarTrm servicioConsultarTrm;

    public ServicioCobrarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva, ServicioConsultarTrm servicioConsultarTrm) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
        this.servicioConsultarTrm = servicioConsultarTrm;
    }

    public DtoReservaCobro ejecutar(int numeroReserva) {
        validarExisteReserva(numeroReserva);
        DtoReserva reservaConsultada = daoReserva.obtenerReserva(numeroReserva);
        Reserva reserva = new Reserva(reservaConsultada.getNumeroReserva(), reservaConsultada.getNombre(),
                EstadoReserva.TERMINADA.getEstado());
        repositorioReserva.actualizar(reserva);
        double valorTrm = 1;
        String erroresProcesamiento = null;
        try {
            valorTrm = obtenerValorTrm(reservaConsultada.getFechaSalida());
        } catch (ExcepcionTrm excepcionTrm) {
            LOGGER.error(excepcionTrm.getMessage());
            erroresProcesamiento = excepcionTrm.getMessage();
        }
        double valorDolares = darFormatoDosDecimales((reservaConsultada.getCostoTotal() / valorTrm));
        return new DtoReservaCobro(numeroReserva, reservaConsultada.getNumeroHabitacion(), reservaConsultada.getFechaSalida(),
                reservaConsultada.getCostoTotal(), valorDolares, valorTrm, erroresProcesamiento);
    }

    private double obtenerValorTrm(LocalDateTime fechaSalida) {
        return servicioConsultarTrm.ejecutar(fechaSalida.toLocalDate());
    }

    private void validarExisteReserva(int numeroReserva) {
        if (!repositorioReserva.existeReserva(numeroReserva)) {
            throw new ExcepcionSinDatos(LA_RESERVA_NO_EXISTE);
        }
    }
}
