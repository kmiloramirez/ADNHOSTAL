package com.ceiba.trm.controlador;

import com.ceiba.trm.consulta.ManejadorConsultaTrm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/trm")
@Api(tags = {"Consulta comando trm"})
public class ConsultaControladorTrm {

    private final ManejadorConsultaTrm manejadorConsultaTrm;

    public ConsultaControladorTrm(ManejadorConsultaTrm manejadorConsultaTrm) {
        this.manejadorConsultaTrm = manejadorConsultaTrm;
    }

    @GetMapping
    @ApiOperation("Consultar Trm Del Dia")
    public double consultarTrmDia() {
        return manejadorConsultaTrm.ejecutar(LocalDateTime.now());
    }
}
