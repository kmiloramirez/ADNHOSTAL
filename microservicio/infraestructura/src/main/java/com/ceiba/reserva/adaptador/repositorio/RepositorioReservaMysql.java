package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {
    @Override
    public Long crear(Reserva reserva) {
        return 1L;
    }

    @Override
    public boolean existeHabitacion(String numeroHabitacion) {
        return true;
    }

    @Override
    public boolean disponibilidadHabitacion(String numeroHabitacion, LocalDate fechaEntrada, LocalDate fechaSalida) {
        return true;
    }

    @Override
    public double precioHabitacion(String numeroHabitacion) {
        return 100000;
    }
}
