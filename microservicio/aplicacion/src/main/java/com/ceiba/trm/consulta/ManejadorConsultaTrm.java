package com.ceiba.trm.consulta;

import com.ceiba.trm.servicio.ServicioConsultarTrm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ManejadorConsultaTrm {

    private final ServicioConsultarTrm servicioConsultarTrm;

    public ManejadorConsultaTrm(ServicioConsultarTrm servicioConsultarTrm) {
        this.servicioConsultarTrm = servicioConsultarTrm;
    }

    public double ejecutar(LocalDate fechaConsulta) {
        return servicioConsultarTrm.ejecutar(fechaConsulta);
    }
}
