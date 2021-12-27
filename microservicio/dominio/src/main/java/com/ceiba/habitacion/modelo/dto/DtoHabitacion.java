package com.ceiba.habitacion.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DtoHabitacion {

    private Long id;
    private String numero;
    private int camas;
    private double precio;
    private String descripcion;
}
