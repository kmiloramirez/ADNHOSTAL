package com.ceiba.reserva.comando.manejador;

import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.servicio.ServicioCobrarReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCobroReserva {

    private final ServicioCobrarReserva servicioCobrarReserva;

    public ManejadorCobroReserva(ServicioCobrarReserva servicioCobrarReserva) {
        this.servicioCobrarReserva = servicioCobrarReserva;
    }

    public DtoReservaCobro ejecutar(Integer numeroReserva) {
        return servicioCobrarReserva.ejecutar(numeroReserva);
    }
}
