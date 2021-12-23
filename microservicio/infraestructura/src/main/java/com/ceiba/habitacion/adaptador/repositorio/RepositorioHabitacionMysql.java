package com.ceiba.habitacion.adaptador.repositorio;

import com.ceiba.habitacion.modelo.entidad.Habitacion;
import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioHabitacionMysql implements RepositorioHabitacion {

    @Override
    public Long crear(Habitacion habitacion) {
        return 1l;
    }

    @Override
    public boolean existe(String numero) {
        return true;
    }
}
