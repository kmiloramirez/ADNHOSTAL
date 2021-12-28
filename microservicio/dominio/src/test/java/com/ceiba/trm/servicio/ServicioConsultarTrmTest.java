package com.ceiba.trm.servicio;

import com.ceiba.trm.puerto.repositorio.RepositorioTrm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ServicioConsultarTrmTest {

    private ServicioConsultarTrm servicioConsultarTrm;
    private RepositorioTrm repositorioTrm;

    @BeforeEach
    void setUp() {
        repositorioTrm = Mockito.mock(RepositorioTrm.class);
        servicioConsultarTrm = new ServicioConsultarTrm(repositorioTrm);
    }

    @Test
    void ejecutar() {
        double valorTrm = 4000.1899;
        double valorTrmEsperado = 4000.19;
        LocalDate fechaConsultar = LocalDate.now();
        Mockito.doReturn(valorTrm).when(repositorioTrm).obtenerTrm(fechaConsultar);

        double resultado = servicioConsultarTrm.ejecutar(fechaConsultar);

        assertEquals(valorTrmEsperado,resultado);
    }
}