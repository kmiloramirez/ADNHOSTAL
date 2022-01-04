package com.ceiba.reserva.comando.manejador;

import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.consulta.ManejadorConsultaDolaresReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import com.ceiba.reserva.servicio.ServicioCobrarDolaresReserva;
import com.ceiba.trm.servicio.ServicioConsultarTrm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCobroReserva {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManejadorCobroReserva.class);

    private final ServicioCobrarDolaresReserva servicioCobrarReserva;
    private final ServicioActualizarReserva servicioActualizarReserva;
    private final DaoReserva daoReserva;
    private final FabricaReserva fabricaReserva;
    private final ServicioConsultarTrm servicioConsultarTrm;
    private final ManejadorConsultaDolaresReserva manejadorConsultaDolaresReserva;

    public ManejadorCobroReserva(ServicioCobrarDolaresReserva servicioCobrarReserva, ServicioActualizarReserva servicioActualizarReserva, DaoReserva daoReserva, FabricaReserva fabricaReserva, ServicioConsultarTrm servicioConsultarTrm, ManejadorConsultaDolaresReserva manejadorConsultaDolaresReserva) {
        this.servicioCobrarReserva = servicioCobrarReserva;
        this.servicioActualizarReserva = servicioActualizarReserva;
        this.daoReserva = daoReserva;
        this.fabricaReserva = fabricaReserva;
        this.servicioConsultarTrm = servicioConsultarTrm;
        this.manejadorConsultaDolaresReserva = manejadorConsultaDolaresReserva;
    }

    public DtoReservaCobro ejecutar(Integer numeroReserva) {
        DtoReserva reservaConsultada = daoReserva.obtenerReserva(numeroReserva);
        Reserva reserva = fabricaReserva.crearReservaTerminada(reservaConsultada);
        servicioActualizarReserva.ejecutar(reserva);
        return  manejadorConsultaDolaresReserva.ejecutar(numeroReserva);
    }
}
