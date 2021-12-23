package com.ceiba.habitacion.puerto.repositorio;

import com.ceiba.habitacion.modelo.entidad.Habitacion;

public interface RepositorioHabitacion {

    /**
     * Permite crear una habitacion
     *
     * @param habitacion
     * @return el id generado
     */
    Long crear(Habitacion habitacion);

    /**
     * Permite validar si existe una habitacion por su numero
     *
     * @param numero
     * @return si existe o no
     */
    boolean existe(String numero);
}
