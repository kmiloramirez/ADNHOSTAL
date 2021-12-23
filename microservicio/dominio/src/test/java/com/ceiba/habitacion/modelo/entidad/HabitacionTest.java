package com.ceiba.habitacion.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.habitacion.modelo.testdatabuilder.HabitacionTestDataBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HabitacionTest {

    @Test
    void crearHabitacionSinError (){
      Habitacion habitacion = new HabitacionTestDataBuilder().build();

      assertEquals("101" ,habitacion.getNumero());
      assertEquals(1 ,habitacion.getCamas());
      assertEquals(120000 ,habitacion.getPrecio());
      assertEquals("prueba" ,habitacion.getDescripcion());
    }

    @Test
    void crearHabitacionConErrorNumero (){
        HabitacionTestDataBuilder habitacionTestDataBuilder = new HabitacionTestDataBuilder().conNumero(null);


        BasePrueba.assertThrows(() -> {
                    habitacionTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe agregar un numero de habitacion");

    }

    @Test
    void crearHabitacionConErrorNumeroVacio (){
        HabitacionTestDataBuilder habitacionTestDataBuilder = new HabitacionTestDataBuilder().conNumero("");


        BasePrueba.assertThrows(() -> {
                    habitacionTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe agregar un numero de habitacion");

    }

    @Test
    void crearHabitacionConErrorCamasMinimas (){
        HabitacionTestDataBuilder habitacionTestDataBuilder = new HabitacionTestDataBuilder().conCamas(0);

        BasePrueba.assertThrows(() -> {
                    habitacionTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La habitacion debe tener como minimo: 1");
    }

    @Test
    void crearHabitacionConErrorPrecioNegativo (){
        HabitacionTestDataBuilder habitacionTestDataBuilder = new HabitacionTestDataBuilder().conPrecio(-10);

        BasePrueba.assertThrows(() -> {
                    habitacionTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El precio de la habitacion debe ser positivo");
    }

    @Test
    void crearHabitacionConErrorCamasMaximas (){
        HabitacionTestDataBuilder habitacionTestDataBuilder = new HabitacionTestDataBuilder().conCamas(4);

        BasePrueba.assertThrows(() -> {
                    habitacionTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La habitacion debe tener como maximo: 3");
    }

    @Test
    void crearHabitacionConErrorDescripcion (){
        HabitacionTestDataBuilder habitacionTestDataBuilder = new HabitacionTestDataBuilder().conDescripcion(null);


        BasePrueba.assertThrows(() -> {
                    habitacionTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe agregar un descripcion de habitacion");

    }

    @Test
    void crearHabitacionConErrorDescripcionVacio (){
        HabitacionTestDataBuilder habitacionTestDataBuilder = new HabitacionTestDataBuilder().conDescripcion("");


        BasePrueba.assertThrows(() -> {
                    habitacionTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe agregar un descripcion de habitacion");

    }

}