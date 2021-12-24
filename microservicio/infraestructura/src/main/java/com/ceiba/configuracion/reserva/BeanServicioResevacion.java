package com.ceiba.configuracion.reserva;

import com.ceiba.habitacion.puerto.dao.DaoHabitacion;
import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;
import com.ceiba.reserva.cobro.*;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioCalcularPrecioReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanServicioResevacion {

    @Bean
    public ServicioCalcularPrecioReserva servicioCalcularPrecioReserva(RepositorioReserva repositorioReserva, DaoHabitacion daoHabitacion) {
        List<ReglaCobro> reglaCobros = new ArrayList<>();
        reglaCobros.add(new ReglaCobroLunesAMiercoles());
        reglaCobros.add(new ReglaCobroOrdinaria());
        reglaCobros.add(new ReglaCobroSabadoYDomingo());
        reglaCobros.add(new ReglaCobroDescuentoPorDias());
        return new ServicioCalcularPrecioReserva(reglaCobros, repositorioReserva, daoHabitacion);
    }

    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva, RepositorioHabitacion repositorioHabitacion,
                                                     ServicioCalcularPrecioReserva servicioCalcularPrecioReserva) {
        return new ServicioCrearReserva(repositorioReserva, repositorioHabitacion, servicioCalcularPrecioReserva);
    }
}
