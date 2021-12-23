package com.ceiba.habitacion.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoHabitacion {

    private String numero;
    private int camas;
    private double precio;
    private String descripcion;

}
