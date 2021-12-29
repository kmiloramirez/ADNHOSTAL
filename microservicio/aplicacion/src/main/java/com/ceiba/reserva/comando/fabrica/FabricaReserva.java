package com.ceiba.reserva.comando.fabrica;

import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import org.springframework.stereotype.Component;


@Component
public class FabricaReserva {

    public Reserva crear(ComandoReserva comandoReserva) {
        return new Reserva(comandoReserva.getNombre(), comandoReserva.getFechaEntrada(), comandoReserva.getNumeroHabitacion(),
                comandoReserva.getFechaSalida());
    }

    public Reserva crearActualizar(ComandoReserva comandoReserva) {
        return new Reserva(comandoReserva.getNumeroReserva(), comandoReserva.getNombre(),
                comandoReserva.getEstadoReserva());
    }

    public Reserva crearReservaTerminada(DtoReserva dtoReserva){
        return  new Reserva(dtoReserva.getNumeroReserva(), dtoReserva.getNombre(), dtoReserva.getFechaEntrada(),
                dtoReserva.getNumeroHabitacion(), dtoReserva.getFechaSalida(), dtoReserva.getFechaRegistro(),
                dtoReserva.getCostoTotal(), EstadoReserva.TERMINADA.getEstado());

    }

}
