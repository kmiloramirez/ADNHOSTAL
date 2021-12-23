package com.ceiba.habitacion.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.habitacion.modelo.entidad.Habitacion;
import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;

public class ServicioCrearHabitacion {

    private static final String LA_HABITCION_EXISTE = "Ya existe esta habitcion";

    private final RepositorioHabitacion repositorioHabitacion;

    public ServicioCrearHabitacion(RepositorioHabitacion repositorioHabitacion) {
        this.repositorioHabitacion = repositorioHabitacion;
    }

    public Long ejecutar(Habitacion habitacion) {
        existeHabitacion(habitacion.getNumero());
        return repositorioHabitacion.crear(habitacion);

    }

    private void existeHabitacion(String numeroHabitacion) {
        if (repositorioHabitacion.existe(numeroHabitacion)) {
            throw new ExcepcionDuplicidad(LA_HABITCION_EXISTE);
        }
    }
}
