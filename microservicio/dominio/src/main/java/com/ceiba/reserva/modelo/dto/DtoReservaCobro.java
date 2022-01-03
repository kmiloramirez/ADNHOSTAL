package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DtoReservaCobro {

    private int id;
    private String numeroHabitacion;
    private LocalDateTime fechaSalida;
    private double costoTotalPesos;
    @Setter
    private double costoTotalDolares;
    @Setter
    private double trm;
    @Setter
    private String erroresProcesamiento;

}
