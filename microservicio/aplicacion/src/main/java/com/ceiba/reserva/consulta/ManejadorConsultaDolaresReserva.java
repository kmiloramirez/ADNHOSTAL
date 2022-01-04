package com.ceiba.reserva.consulta;

import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.comando.manejador.ManejadorCobroReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.servicio.ServicioCobrarDolaresReserva;
import com.ceiba.trm.servicio.ServicioConsultarTrm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultaDolaresReserva {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManejadorConsultaDolaresReserva.class);


    private final DaoReserva daoReserva;
    private final FabricaReserva fabricaReserva;
    private final ServicioConsultarTrm servicioConsultarTrm;
    private final ServicioCobrarDolaresReserva servicioCobrarReserva;


    public ManejadorConsultaDolaresReserva(DaoReserva daoReserva, ServicioCobrarDolaresReserva servicioCobrarReserva,
                                           FabricaReserva fabricaReserva, ServicioConsultarTrm servicioConsultarTrm) {
        this.daoReserva = daoReserva;
        this.servicioCobrarReserva = servicioCobrarReserva;
        this.fabricaReserva = fabricaReserva;
        this.servicioConsultarTrm = servicioConsultarTrm;
    }

    public DtoReservaCobro ejecutar(int numeroReserva){
        DtoReserva reservaConsultada = daoReserva.obtenerReserva(numeroReserva);
        DtoReservaCobro reservaCobro = fabricaReserva.crearResevaCobro(reservaConsultada);
        try {
            reservaCobro.setTrm(servicioConsultarTrm.ejecutar(reservaConsultada.getFechaSalida()));
        } catch (RuntimeException excepcionTrm) {
            LOGGER.info(excepcionTrm.getMessage(), excepcionTrm);
            reservaCobro.setErroresProcesamiento(excepcionTrm.getMessage());
        }
        servicioCobrarReserva.ejecutar(reservaCobro);
        return reservaCobro;
    }
}
