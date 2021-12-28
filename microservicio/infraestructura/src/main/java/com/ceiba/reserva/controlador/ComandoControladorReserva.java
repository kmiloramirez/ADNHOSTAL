package com.ceiba.reserva.controlador;


import com.ceiba.ComandoRespuesta;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.manejador.ManejadorActualizarReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCobroReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCrearReserva;
import com.ceiba.reserva.comando.manejador.ManejadorEliminarReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
@Api(tags = {"Controlador comando reserva"})
public class ComandoControladorReserva {

    private final ManejadorCrearReserva manejadorCrearReserva;
    private final ManejadorActualizarReserva manejadorActualizarReserva;
    private final ManejadorEliminarReserva manejadorEliminarReserva;
    private final ManejadorCobroReserva manejadorCobroReserva;

    public ComandoControladorReserva(ManejadorCrearReserva manejadorCrearReserva,
                                     ManejadorActualizarReserva manejadorActualizarReserva,
                                     ManejadorEliminarReserva manejadorEliminarReserva, ManejadorCobroReserva manejadorCobroReserva) {
        this.manejadorCrearReserva = manejadorCrearReserva;
        this.manejadorActualizarReserva = manejadorActualizarReserva;
        this.manejadorEliminarReserva = manejadorEliminarReserva;
        this.manejadorCobroReserva = manejadorCobroReserva;
    }

    @PostMapping
    @ApiOperation("Crear Reservacion")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoReserva comandoReserva) {
        return manejadorCrearReserva.ejecutar(comandoReserva);
    }

    @PatchMapping
    @ApiOperation("Actualizar Estado Reserva")
    public void actualizarEstado (@RequestBody ComandoReserva comandoReserva){
        manejadorActualizarReserva.ejecutar(comandoReserva);
    }

    @DeleteMapping
    @ApiOperation("Elimina Reserva")
    public void eliminarReserva (@RequestParam Integer numeroReserva){
        manejadorEliminarReserva.ejecutar(numeroReserva);
    }

    @PutMapping
    @ApiOperation("Cobrar La Reserva")
    public DtoReservaCobro cobrarReserva(@RequestParam Integer numeroReserva){
        return manejadorCobroReserva.ejecutar(numeroReserva);
    }
}
