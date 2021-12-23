package com.ceiba.reserva.servicio;

import com.ceiba.reserva.cobro.ReglaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.util.List;

public class ServicioCalcularPrecioReserva {

    private final List<ReglaCobro> reglasCobros;
    private final RepositorioReserva repositorioReserva;

    public ServicioCalcularPrecioReserva(List<ReglaCobro> reglaCobros, RepositorioReserva repositorioReserva) {
        this.reglasCobros = reglaCobros;
        this.repositorioReserva = repositorioReserva;
    }

    public Reserva ejecutar(Reserva reserva){
        double precioHabitacion = repositorioReserva.precioHabitacion(reserva.getNumeroHabitacion());
        reglasCobros.forEach(reglaCobro -> {
            double precioRegla =reglaCobro.cobrar(reserva.getFechaEntrada(),reserva.getFechaSalida(),precioHabitacion);
            reserva.setCostoTotal(reserva.getCostoTotal()+precioRegla);
        });
        return reserva;
    }

}
