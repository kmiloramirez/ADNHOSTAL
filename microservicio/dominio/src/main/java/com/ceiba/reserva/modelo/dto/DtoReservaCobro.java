package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DtoReservaCobro {

    private int id;
    private String numeroHabitacion;
    private LocalDateTime fechaSalida;
    private double costoTotalPesos;
    private double costoTotalDolares;
    private double trm;
    private String erroresProcesamiento;

}
