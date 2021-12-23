package com.ceiba.reserva.modelo.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;

public class ReservaTestDataBuilder {

    private String nombre;
    private LocalDate fechaEntrada;
    private String numeroHabitacion;
    private LocalDate fechaSalida;
    private LocalDate fechaRegistro;


    public ReservaTestDataBuilder() {
        LocalDate fechaActual = LocalDate.now();
        this.nombre = "prueba";
        this.fechaEntrada = fechaActual.plusDays(1);
        this.numeroHabitacion = "prueba";
        this.fechaSalida = this.fechaEntrada.plusDays(1);
        this.fechaRegistro = fechaActual;
    }

    public ReservaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ReservaTestDataBuilder conFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public ReservaTestDataBuilder conNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
        return this;
    }

    public ReservaTestDataBuilder conFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public ReservaTestDataBuilder conFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }


    public Reserva build() {
        return new Reserva(nombre, fechaEntrada, numeroHabitacion, fechaSalida, fechaRegistro);
    }


}
