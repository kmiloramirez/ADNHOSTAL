package com.ceiba.habitacion.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DtoHabitacion {

    private Long id;
    private String numero;
    private int camas;
    private double precio;
    private String descripcion;
}
