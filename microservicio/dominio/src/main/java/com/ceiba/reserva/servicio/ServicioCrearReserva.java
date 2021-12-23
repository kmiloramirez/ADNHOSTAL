package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDate;

public class ServicioCrearReserva {

    private static final String LA_HABITCION_NO_EXISTE = "La habitacion en la que intenta reservar no existe";
    private static final String LA_HABITCION_NO_DISPONIBLE = "La habitacion no esta disponible en esas fechas";

    private final RepositorioReserva repositorioReserva;
    private final ServicioCalcularPrecioReserva servicioCalcularPrecioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva, ServicioCalcularPrecioReserva servicioCalcularPrecioReserva) {
        this.repositorioReserva = repositorioReserva;
        this.servicioCalcularPrecioReserva = servicioCalcularPrecioReserva;
    }

    public Long ejecutar(Reserva reserva){
        validarHabitacionExiste(reserva.getNumeroHabitacion());
        validarDisponibilidad(reserva.getNumeroHabitacion(),reserva.getFechaEntrada(),reserva.getFechaSalida());
        reserva.setEstadoReserva(EstadoReserva.RESEVADO.getEstado());
        reserva = servicioCalcularPrecioReserva.ejecutar(reserva);
        return repositorioReserva.crear(reserva);
    }

    private void validarHabitacionExiste(String numeroHabitacion) {
        if(!repositorioReserva.existeHabitacion(numeroHabitacion)){
            throw new ExcepcionSinDatos(LA_HABITCION_NO_EXISTE);
        }
    }

    private void validarDisponibilidad(String numeroHabitacion, LocalDate fechaEntrada, LocalDate fechaSalida) {
        if(!repositorioReserva.disponibilidadHabitacion(numeroHabitacion,fechaEntrada,fechaSalida)){
            throw new ExcepcionDuplicidad(LA_HABITCION_NO_DISPONIBLE);
        }
    }
}
