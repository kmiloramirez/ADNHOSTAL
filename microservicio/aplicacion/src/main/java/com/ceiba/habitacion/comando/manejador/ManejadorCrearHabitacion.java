package com.ceiba.habitacion.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.habitacion.comando.ComandoHabitacion;
import com.ceiba.habitacion.comando.fabrica.FabricaHabitacion;
import com.ceiba.habitacion.modelo.entidad.Habitacion;
import com.ceiba.habitacion.servicio.ServicioCrearHabitacion;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearHabitacion implements ManejadorComandoRespuesta<ComandoHabitacion, ComandoRespuesta<Long>> {

    private final FabricaHabitacion fabricaHabitacion;
    private final ServicioCrearHabitacion servicioCrearHabitacion;


    public ManejadorCrearHabitacion(FabricaHabitacion fabricaHabitacion, ServicioCrearHabitacion servicioCrearHabitacion) {
        this.fabricaHabitacion = fabricaHabitacion;
        this.servicioCrearHabitacion = servicioCrearHabitacion;
    }


    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoHabitacion comandoHabitacion) {
        Habitacion habitacion = fabricaHabitacion.crear(comandoHabitacion);
        return new ComandoRespuesta<>(this.servicioCrearHabitacion.ejecutar(habitacion));
    }
}
