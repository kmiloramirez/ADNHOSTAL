package com.ceiba.reserva.controlador;


import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.ComandoReserva;
import com.ceiba.reserva.manejador.ManejadorCrearReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
@Api(tags = {"Controlador comando reserva"})
public class ComandoControladorReserva {

    private final ManejadorCrearReserva manejadorCrearReserva;

    public ComandoControladorReserva(ManejadorCrearReserva manejadorCrearReserva) {
        this.manejadorCrearReserva = manejadorCrearReserva;
    }

    @PostMapping
    @ApiOperation("Crear Reservacion")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoReserva comandoReserva) {
        return manejadorCrearReserva.ejecutar(comandoReserva);
    }
}
