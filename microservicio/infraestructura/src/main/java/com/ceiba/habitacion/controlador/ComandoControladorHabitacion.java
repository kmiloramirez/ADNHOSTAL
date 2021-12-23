package com.ceiba.habitacion.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.habitacion.comando.ComandoHabitacion;
import com.ceiba.habitacion.comando.manejador.ManejadorCrearHabitacion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habitacion")
@Api(tags = { "Controlador comando habitacion"})
public class ComandoControladorHabitacion {

    private final ManejadorCrearHabitacion manejadorCrearHabitacion;

    public ComandoControladorHabitacion(ManejadorCrearHabitacion manejadorCrearHabitacion) {
        this.manejadorCrearHabitacion = manejadorCrearHabitacion;
    }

    @PostMapping
    @ApiOperation("Crear Habitacion")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoHabitacion comandoHabitacion) {
        return manejadorCrearHabitacion.ejecutar(comandoHabitacion);
    }
}
