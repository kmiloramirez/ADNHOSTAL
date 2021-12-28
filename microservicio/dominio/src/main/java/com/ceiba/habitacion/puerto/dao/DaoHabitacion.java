package com.ceiba.habitacion.puerto.dao;

import com.ceiba.habitacion.modelo.dto.DtoHabitacion;

import java.util.List;

public interface DaoHabitacion {

    /**
     * Permite obtener las habitaciones
     *
     * @return habitaciones
     */
    List<DtoHabitacion> listar();

    /**
     * Permite obtener el precio de una habitacion
     *
     * @param numero
     * @return valor habitacion
     */
    double obtenerPrecioHabitacion(String numero);
}
