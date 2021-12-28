package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultaReserva {

    private final DaoReserva daoReserva;

    public ManejadorConsultaReserva(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public DtoReserva obtenerReserva(int numeroReserva) {
        return daoReserva.obtenerReserva(numeroReserva);
    }

    public List<DtoReserva> listar() {
        return daoReserva.listar();
    }

    public List<DtoReserva> listarPorEstado(String estado) {
        return daoReserva.consultarReservaEstado(estado);
    }

    public List<String> listarEstados() {
        return EstadoReserva.getEstados();
    }

}
