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
    private final ServicioConsultarTrm servicioConsultarTrm;

    public ServicioCobrarReserva(RepositorioReserva repositorioReserva, ServicioConsultarTrm servicioConsultarTrm) {
        this.repositorioReserva = repositorioReserva;
        this.servicioConsultarTrm = servicioConsultarTrm;
    }

    public DtoReservaCobro ejecutar(Reserva reserva) {
        double valorTrm = 1;
        String erroresProcesamiento = null;
        try {
            valorTrm = obtenerValorTrm(reserva.getFechaSalida());
        } catch (ExcepcionTrm excepcionTrm) {
            LOGGER.info(excepcionTrm.getMessage());
            erroresProcesamiento = excepcionTrm.getMessage();
        }
        double valorDolares = darFormatoDosDecimales((reserva.getCostoTotal() / valorTrm));
        return new DtoReservaCobro(reserva.getId(), reserva.getNumeroHabitacion(), reserva.getFechaSalida(),
                reserva.getCostoTotal(), valorDolares, valorTrm, erroresProcesamiento);
    }

    private double obtenerValorTrm(LocalDateTime fechaSalida) {
        return servicioConsultarTrm.ejecutar(fechaSalida.toLocalDate());
    }

}
