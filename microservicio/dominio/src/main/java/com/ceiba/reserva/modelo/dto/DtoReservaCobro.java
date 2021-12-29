package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
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
