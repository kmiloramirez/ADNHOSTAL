package com.ceiba.reserva.modelo.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private int id;
    private String nombre;
    private LocalDateTime fechaEntrada;
    private String numeroHabitacion;
    private LocalDateTime fechaSalida;
    private LocalDate fechaRegistro;
    private double costoTotal;
    private String estadoReserva;



    public ReservaTestDataBuilder() {
        this.id = 1;
        LocalDateTime fechaActual = LocalDateTime.now();
        this.nombre = "prueba";
        this.fechaEntrada = fechaActual.plusDays(1);
        this.numeroHabitacion = "prueba";
        this.fechaSalida = this.fechaEntrada.plusDays(1);
        this.fechaRegistro = fechaActual.toLocalDate();
        this.costoTotal = 100000.0;
        this.estadoReserva = "reservado";
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

    public ReservaTestDataBuilder conId(int id) {
        this.id = id;
        return this;
    }

    public ReservaTestDataBuilder conEstado(String estadoReserva) {
        this.estadoReserva = estadoReserva;
        return this;
    }

    public Reserva build() {
        return new Reserva(nombre, fechaEntrada, numeroHabitacion, fechaSalida);
    }

    public Reserva buildActualizar() {
        return new Reserva(id, nombre, estadoReserva);
    }
    public Reserva buildConTodosLosDatos() {
        return new Reserva(id, nombre,fechaEntrada,numeroHabitacion,fechaSalida,fechaRegistro,costoTotal, estadoReserva);
    }

    public ReservaTestDataBuilder conCostoTotal(double costoTotal) {
        this.costoTotal =costoTotal;
        return this;

    }


}
