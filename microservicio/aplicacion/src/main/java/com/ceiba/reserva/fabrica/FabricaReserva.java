package com.ceiba.reserva.fabrica;

import com.ceiba.reserva.ComandoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.stereotype.Component;

@Component
public class FabricaReserva {

    public Reserva crear(ComandoReserva comandoReserva){
        return new Reserva(comandoReserva.getNombre(),comandoReserva.getFechaEntrada(), comandoReserva.getNumeroHabitacion(),
                comandoReserva.getFechaSalida(),comandoReserva.getFechaRegistro());
    }

}
