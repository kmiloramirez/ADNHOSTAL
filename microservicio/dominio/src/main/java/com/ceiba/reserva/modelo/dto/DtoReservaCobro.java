package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DtoReservaCobro {

    private int numeroReserva;
    private String numeroHabitacion;
    private double costoTotalPesos;
    private double costoTotalDolares;

}
