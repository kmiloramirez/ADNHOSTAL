package com.ceiba.reserva.controlador;

import com.ceiba.reserva.consulta.ManejadorConsultaDolaresReserva;
import com.ceiba.reserva.consulta.ManejadorConsultaReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@Api(tags = {"Consulta comando reserva"})
public class ConsultaControladorReserva {

    private final ManejadorConsultaReserva manejadorConsultaReserva;
    private final ManejadorConsultaDolaresReserva manejadorConsultaDolaresReservaReserva;

    public ConsultaControladorReserva(ManejadorConsultaReserva manejadorConsultaReserva, ManejadorConsultaDolaresReserva manejadorConsultaDolaresReservaReserva) {
        this.manejadorConsultaReserva = manejadorConsultaReserva;
        this.manejadorConsultaDolaresReservaReserva = manejadorConsultaDolaresReservaReserva;
    }

    @GetMapping
    @ApiOperation("Obtener reservas")
    public DtoReserva obtenerReserva(@RequestParam int numeroReserva) {
        return manejadorConsultaReserva.obtenerReserva(numeroReserva);
    }

    @GetMapping("lista-reservas")
    @ApiOperation("Listar Reservas")
    public List<DtoReserva> listar() {
        return manejadorConsultaReserva.listar();
    }

    @GetMapping("estados")
    @ApiOperation("Listar Estados Reservas")
    public List<String> listarEstados() {
        return manejadorConsultaReserva.listarEstados();
    }

    @GetMapping("reserva-estado")
    @ApiOperation("Reservas Por Estados")
    public List<DtoReserva> listarReservasEstado(@RequestParam String estado) {
        return manejadorConsultaReserva.listarPorEstado(estado);
    }

    @GetMapping("valor-dolares")
    @ApiOperation("Reserva Valor Dolares")
    public DtoReservaCobro listarReservasEstado(@RequestParam int numeroReserva) {
        return manejadorConsultaDolaresReservaReserva.ejecutar(numeroReserva);
    }


}
