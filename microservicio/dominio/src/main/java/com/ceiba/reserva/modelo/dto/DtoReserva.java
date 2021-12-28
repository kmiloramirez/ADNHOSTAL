package com.ceiba.reserva.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DtoReserva {
    private int numeroReserva;
    private String nombre;
    private LocalDateTime fechaEntrada;
    private String numeroHabitacion;
    private LocalDateTime fechaSalida;
    private LocalDate fechaRegistro;
    private double costoTotal;
    private String estadoReserva;
}
