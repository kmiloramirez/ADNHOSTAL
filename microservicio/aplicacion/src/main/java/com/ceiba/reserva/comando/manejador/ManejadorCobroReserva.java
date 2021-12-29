package com.ceiba.reserva.comando.manejador;

import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import com.ceiba.reserva.servicio.ServicioCobrarReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCobroReserva {

    private final ServicioCobrarReserva servicioCobrarReserva;
    private final ServicioActualizarReserva servicioActualizarReserva;
    private final DaoReserva daoReserva;
    private final FabricaReserva fabricaReserva;

    public ManejadorCobroReserva(ServicioCobrarReserva servicioCobrarReserva, ServicioActualizarReserva servicioActualizarReserva, DaoReserva daoReserva, FabricaReserva fabricaReserva) {
        this.servicioCobrarReserva = servicioCobrarReserva;
        this.servicioActualizarReserva = servicioActualizarReserva;
        this.daoReserva = daoReserva;
        this.fabricaReserva = fabricaReserva;
    }

    public DtoReservaCobro ejecutar(Integer numeroReserva) {
        DtoReserva reservaConsultada = daoReserva.obtenerReserva(numeroReserva);
        Reserva reserva = fabricaReserva.crearReservaTerminada(reservaConsultada);
        servicioActualizarReserva.ejecutar(reserva);
        return servicioCobrarReserva.ejecutar(reserva);
    }
}
