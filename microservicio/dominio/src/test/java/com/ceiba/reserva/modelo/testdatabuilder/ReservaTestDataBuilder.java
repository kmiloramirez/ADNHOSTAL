package com.ceiba.reserva.modelo.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private String nombre;
    private LocalDateTime fechaEntrada;
    private String numeroHabitacion;
    private LocalDateTime fechaSalida;


    public ReservaTestDataBuilder() {
        LocalDateTime fechaActual = LocalDateTime.now();
        this.nombre = "prueba";
        this.fechaEntrada = fechaActual.plusDays(1);
        this.numeroHabitacion = "prueba";
        this.fechaSalida = this.fechaEntrada.plusDays(1);
    }

    public ReservaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ReservaTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public ReservaTestDataBuilder conNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
        return this;
    }

    public ReservaTestDataBuilder conFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public Reserva build() {
        return new Reserva(nombre, fechaEntrada, numeroHabitacion, fechaSalida);
    }


}
