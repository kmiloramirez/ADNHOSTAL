package com.ceiba.reserva.comando.manejador;

import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.consulta.ManejadorConsultaDolaresReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import com.ceiba.trm.servicio.ServicioConsultarTrm;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCobroReserva implements ManejadorComandoRespuesta<Integer,DtoReservaCobro> {

    private final ServicioActualizarReserva servicioActualizarReserva;
    private final DaoReserva daoReserva;
    private final FabricaReserva fabricaReserva;
    private final ServicioConsultarTrm servicioConsultarTrm;
    private final ManejadorConsultaDolaresReserva manejadorConsultaDolaresReserva;

    public ManejadorCobroReserva(ServicioActualizarReserva servicioActualizarReserva, DaoReserva daoReserva, FabricaReserva fabricaReserva, ServicioConsultarTrm servicioConsultarTrm, ManejadorConsultaDolaresReserva manejadorConsultaDolaresReserva) {
        this.servicioActualizarReserva = servicioActualizarReserva;
        this.daoReserva = daoReserva;
        this.fabricaReserva = fabricaReserva;
        this.servicioConsultarTrm = servicioConsultarTrm;
        this.manejadorConsultaDolaresReserva = manejadorConsultaDolaresReserva;
    }

    @Override
    public DtoReservaCobro ejecutar(Integer numeroReserva) {
        DtoReserva reservaConsultada = daoReserva.obtenerReserva(numeroReserva);
        Reserva reserva = fabricaReserva.crearReservaTerminada(reservaConsultada);
        servicioActualizarReserva.ejecutar(reserva);
        return  manejadorConsultaDolaresReserva.ejecutar(numeroReserva);
    }
}
