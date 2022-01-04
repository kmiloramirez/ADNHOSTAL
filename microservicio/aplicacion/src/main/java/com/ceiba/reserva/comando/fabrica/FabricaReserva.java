package com.ceiba.reserva.comando.fabrica;

import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import org.springframework.stereotype.Component;


@Component
public class FabricaReserva {

    private static final double CERO_DOLARES = 0.0;
    private static final double CERO_TRM = 0.0;
    private static final String SIN_ERROR = null;

    public Reserva crear(ComandoReserva comandoReserva) {
        return new Reserva(comandoReserva.getNombre(), comandoReserva.getFechaEntrada().atStartOfDay(), comandoReserva.getNumeroHabitacion(),
                comandoReserva.getFechaSalida().atStartOfDay());
    }

    public Reserva crearActualizar(ComandoReserva comandoReserva) {
        return new Reserva(comandoReserva.getNumeroReserva(), comandoReserva.getNombre(),
                comandoReserva.getEstadoReserva());
    }

    public Reserva crearReservaTerminada(DtoReserva dtoReserva) {
        return new Reserva(dtoReserva.getNumeroReserva(), dtoReserva.getNombre(), dtoReserva.getFechaEntrada(),
                dtoReserva.getNumeroHabitacion(), dtoReserva.getFechaSalida(), dtoReserva.getFechaRegistro(),
                dtoReserva.getCostoTotal(), EstadoReserva.TERMINADA.getEstado());

    }

   public DtoReservaCobro crearResevaCobro(DtoReserva reserva) {
        return new DtoReservaCobro(reserva.getNumeroReserva(), reserva.getNumeroHabitacion(), reserva.getFechaSalida(),
                reserva.getCostoTotal(), CERO_DOLARES, CERO_TRM, SIN_ERROR);
    }

}
