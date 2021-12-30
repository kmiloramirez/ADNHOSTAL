package com.ceiba.reserva.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDateTime;

public class ComandoReservaTestDataBuilder {

    private int id;
    private String nombre;
    private LocalDateTime fechaEntrada;
    private String numeroHabitacion;
    private LocalDateTime fechaSalida;
    private double costoTotal;
    private String estadoReserva;

    public ComandoReservaTestDataBuilder() {
        this.id = 1;
        this.nombre = "Juan";
        LocalDateTime fechaActual = LocalDateTime.now();
        this.fechaEntrada = fechaActual.plusDays(1);
        this.numeroHabitacion = "101";
        this.fechaSalida = this.fechaEntrada.plusDays(1);
        this.costoTotal = 100000.0;
        this.estadoReserva = "reservado";
    }

    public ComandoReservaTestDataBuilder conId(int id) {
        this.id = id;
        return this;
    }

    public ComandoReservaTestDataBuilder conEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
        return this;
    }

    public ComandoReserva build() {
        return new ComandoReserva(id, nombre, fechaEntrada, numeroHabitacion, fechaSalida, costoTotal, estadoReserva);
    }
}
