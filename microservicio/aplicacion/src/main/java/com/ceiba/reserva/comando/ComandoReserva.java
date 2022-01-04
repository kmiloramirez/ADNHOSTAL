package com.ceiba.reserva.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoReserva {

    private int numeroReserva;
    private String nombre;
    private LocalDate fechaEntrada;
    private String numeroHabitacion;
    private LocalDate fechaSalida;
    private double costoTotal;
    private String estadoReserva;
}
