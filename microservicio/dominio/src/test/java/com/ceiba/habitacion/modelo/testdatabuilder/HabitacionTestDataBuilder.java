package com.ceiba.habitacion.modelo.testdatabuilder;

import com.ceiba.habitacion.modelo.entidad.Habitacion;

public class HabitacionTestDataBuilder {

    private String numero;
    private int camas;
    private double precio;
    private String descripcion;

    public HabitacionTestDataBuilder() {
        this.numero = "101";
        this.camas = 1;
        this.precio = 120000.00;
        this.descripcion = "prueba";
    }

    public HabitacionTestDataBuilder conNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public HabitacionTestDataBuilder conCamas(int camas) {
        this.camas = camas;
        return this;
    }

    public HabitacionTestDataBuilder conPrecio(double precio) {
        this.precio = precio;
        return this;
    }

    public HabitacionTestDataBuilder conDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public Habitacion build() {
        return new Habitacion(numero, camas, precio, descripcion);
    }
}
