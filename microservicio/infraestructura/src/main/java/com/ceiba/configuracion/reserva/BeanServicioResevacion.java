package com.ceiba.configuracion.reserva;

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
    public ServicioCalcularPrecioReserva servicioCalcularPrecioReserva(RepositorioReserva repositorioReserva) {
        List<ReglaCobro> reglaCobros = new ArrayList<>();
        reglaCobros.add(new ReglaCobroLunesAMiercoles());
        reglaCobros.add(new ReglaCobroOrdinaria());
        reglaCobros.add(new ReglaCobroSabadoYDomingo());
        reglaCobros.add(new ReglaCobroDescuentoPorDias());
        return new ServicioCalcularPrecioReserva(reglaCobros, repositorioReserva);
    }

    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva,
                                                     ServicioCalcularPrecioReserva servicioCalcularPrecioReserva) {
        return new ServicioCrearReserva(repositorioReserva, servicioCalcularPrecioReserva);
    }
}
