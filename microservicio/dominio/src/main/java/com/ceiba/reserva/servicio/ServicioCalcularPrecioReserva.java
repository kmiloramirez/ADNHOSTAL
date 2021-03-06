package com.ceiba.reserva.servicio;

import com.ceiba.habitacion.puerto.dao.DaoHabitacion;
import com.ceiba.reserva.cobro.ReglaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ServicioCalcularPrecioReserva {

    private final List<ReglaCobro> reglasCobros;
    private final DaoHabitacion daoHabitacion;

    public ServicioCalcularPrecioReserva(List<ReglaCobro> reglasCobros, DaoHabitacion daoHabitacion) {
        this.reglasCobros = new ArrayList<>();
        this.reglasCobros.addAll(reglasCobros);
        this.daoHabitacion = daoHabitacion;
    }

    public Reserva ejecutar(Reserva reserva) {
        double precioHabitacion = daoHabitacion.obtenerPrecioHabitacion(reserva.getNumeroHabitacion());
        reglasCobros.forEach(reglaCobro -> {
            double precioRegla = reglaCobro.cobrar(reserva.getFechaEntrada().toLocalDate(),
                    reserva.getFechaSalida().toLocalDate(), precioHabitacion);
            reserva.setCostoTotal(reserva.getCostoTotal() + precioRegla);
        });
        return reserva;
    }

}
