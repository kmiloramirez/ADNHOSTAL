package com.ceiba.habitacion.testdatabuilder;

import com.ceiba.habitacion.comando.ComandoHabitacion;

public class ComandoHabitacionTesDataBuider {

    private String numero;
    private int camas;
    private double precio;
    private String descripcion;

    public ComandoHabitacionTesDataBuider() {
        this.numero = "101";
        this.camas = 1;
        this.precio = 100000.0;
        this.descripcion = "prueba";
    }

    public ComandoHabitacionTesDataBuider conNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public ComandoHabitacion build(){
        return new ComandoHabitacion(numero,camas,precio,descripcion);
    }
}
