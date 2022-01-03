package com.ceiba.reserva.comando.manejador;

import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import com.ceiba.reserva.servicio.ServicioCobrarReserva;
import com.ceiba.trm.servicio.ServicioConsultarTrm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCobroReserva {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManejadorCobroReserva.class);

    private final ServicioCobrarReserva servicioCobrarReserva;
    private final ServicioActualizarReserva servicioActualizarReserva;
    private final DaoReserva daoReserva;
    private final FabricaReserva fabricaReserva;
    private final ServicioConsultarTrm servicioConsultarTrm;

    public ManejadorCobroReserva(ServicioCobrarReserva servicioCobrarReserva, ServicioActualizarReserva servicioActualizarReserva, DaoReserva daoReserva, FabricaReserva fabricaReserva, ServicioConsultarTrm servicioConsultarTrm) {
        this.servicioCobrarReserva = servicioCobrarReserva;
        this.servicioActualizarReserva = servicioActualizarReserva;
        this.daoReserva = daoReserva;
        this.fabricaReserva = fabricaReserva;
        this.servicioConsultarTrm = servicioConsultarTrm;
    }

    public DtoReservaCobro ejecutar(Integer numeroReserva) {
        DtoReserva reservaConsultada = daoReserva.obtenerReserva(numeroReserva);
        Reserva reserva = fabricaReserva.crearReservaTerminada(reservaConsultada);
        servicioActualizarReserva.ejecutar(reserva);
        DtoReservaCobro reservaCobro= fabricaReserva.crearResevaCobro(reserva);
        try {
            reservaCobro.setTrm(servicioConsultarTrm.ejecutar(reserva.getFechaSalida()));
        } catch (RuntimeException excepcionTrm) {
            LOGGER.info(excepcionTrm.getMessage(),excepcionTrm);
            reservaCobro.setErroresProcesamiento(excepcionTrm.getMessage());
        }
        servicioCobrarReserva.ejecutar(reservaCobro);
        return reservaCobro;
    }
}
