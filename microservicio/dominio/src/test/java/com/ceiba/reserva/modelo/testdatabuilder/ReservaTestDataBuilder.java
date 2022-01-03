package com.ceiba.reserva.modelo.testdatabuilder;

import com.ceiba.reserva.modelo.dto.DtoReservaCobro;
import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private static final double CERO_DOLARES = 0.0;

    private static final String SIN_ERROR = null;

    private int id;
    private String nombre;
    private LocalDateTime fechaEntrada;
    private String numeroHabitacion;
    private LocalDateTime fechaSalida;
    private final LocalDate fechaRegistro;
    private double costoTotal;
    private String estadoReserva;
    private double trm;


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
        this.trm = 4000.0;
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
        return new Reserva(id, nombre, fechaEntrada, numeroHabitacion, fechaSalida, fechaRegistro, costoTotal, estadoReserva);
    }

    public ReservaTestDataBuilder conCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
        return this;

    }

    public ReservaTestDataBuilder conTrm(double trm) {
        this.trm = trm;
        return this;
    }

    public DtoReservaCobro buildReservaCobrar() {
        Reserva reserva = buildConTodosLosDatos();
        return new DtoReservaCobro(reserva.getId(), reserva.getNumeroHabitacion(), reserva.getFechaSalida(),
                reserva.getCostoTotal(), CERO_DOLARES, trm, SIN_ERROR);
    }


}
