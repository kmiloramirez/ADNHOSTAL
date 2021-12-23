package com.ceiba.habitacion.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.habitacion.modelo.entidad.Habitacion;
import com.ceiba.habitacion.modelo.testdatabuilder.HabitacionTestDataBuilder;
import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearHabitacionTest {

    private ServicioCrearHabitacion servicioCrearHabitacion;
    private RepositorioHabitacion repositorioHabitacion;

    @BeforeEach
    void setUp() {
        repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);
        servicioCrearHabitacion = new ServicioCrearHabitacion(repositorioHabitacion);
    }

    @Test
    void ejecutarCuandoNoExisteLaHabitacionACrear() {

        Habitacion habitacion = new HabitacionTestDataBuilder().build();
        Mockito.when(repositorioHabitacion.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioHabitacion.crear(habitacion)).thenReturn(10L);

        Long idHabitacion = servicioCrearHabitacion.ejecutar(habitacion);

        assertEquals(10L, idHabitacion);
        Mockito.verify(repositorioHabitacion).crear(habitacion);


    }

    @Test
    void ejecutarCuandoExisteLaHabitacionACrear() {

        Habitacion habitacion = new HabitacionTestDataBuilder().build();
        Mockito.when(repositorioHabitacion.existe(Mockito.anyString())).thenReturn(true);

        BasePrueba.assertThrows(() -> {
            servicioCrearHabitacion.ejecutar(habitacion);
        }, ExcepcionDuplicidad.class, "Ya existe esta habitcion");


    }
}