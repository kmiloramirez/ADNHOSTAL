package com.ceiba.habitacion.puerto.dao;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import java.util.List;

public interface DaoHabitacion {

    /**
     * Permite obtener el precio de una habitacion
     *
     * @param numero
     * @return valor habitacion
     */
    double obtenerPrecioHabitacion(String numero);
}
