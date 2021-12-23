package com.ceiba.configuracion.habitacion;

import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;
import com.ceiba.habitacion.servicio.ServicioCrearHabitacion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioHabitacion {

    @Bean
    public ServicioCrearHabitacion servicioCrearHabitacion(RepositorioHabitacion repositorioHabitacion) {
        return new ServicioCrearHabitacion(repositorioHabitacion);
    }
}
