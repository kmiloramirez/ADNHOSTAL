package com.ceiba.reserva.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.*;


@Getter
public class Reserva {

    private static final String SE_DEBE_INGRESAR_EL_NUMERO_HABITACION = "Se debe ingresar el numero de habitacion";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE = "Se debe ingresar el nombre";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA = "Se debe ingresar la fecha de entrada";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA = "Se debe ingresar la fecha de salida";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_REGISTRO = "Se debe ingresar la fecha de registro";
    private static final String LA_FECHA_DE_ENTRADA_DEBE_SER_MAYOR_O_IGUAL_A_LA_ACTUAL = "La fecha de entrada debe ser mayor o igual a la actual";
    private static final String LA_FECHA_DE_SALIDA_DEBE_SER_MAYOR = "La fecha de salida debe ser mayor a la fecha de entrada";

    private int numeroReserva;
    private final String nombre;
    private final LocalDate fechaEntrada;
    private final String numeroHabitacion;
    private final LocalDate fechaSalida;
    private final LocalDate fechaRegistro;
    @Setter
    private double costoTotal;
    @Setter
    private String estadoReserva;

    public Reserva(String nombre, LocalDate fechaEntrada, String numeroHabitacion, LocalDate fechaSalida, LocalDate fechaRegistro) {

        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarNoVacio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarObligatorio(fechaEntrada, SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA);
        validarObligatorio(numeroHabitacion, SE_DEBE_INGRESAR_EL_NUMERO_HABITACION);
        validarNoVacio(numeroHabitacion, SE_DEBE_INGRESAR_EL_NUMERO_HABITACION);
        validarObligatorio(fechaSalida, SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA);
        validarObligatorio(fechaRegistro, SE_DEBE_INGRESAR_LA_FECHA_DE_REGISTRO);
        validarFechaEntrada(fechaEntrada, LA_FECHA_DE_ENTRADA_DEBE_SER_MAYOR_O_IGUAL_A_LA_ACTUAL);
        validarFechasReserva(fechaEntrada, fechaSalida, LA_FECHA_DE_SALIDA_DEBE_SER_MAYOR);


        this.nombre = nombre;
        this.fechaEntrada = fechaEntrada;
        this.numeroHabitacion = numeroHabitacion;
        this.fechaSalida = fechaSalida;
        this.fechaRegistro = fechaRegistro;
        this.costoTotal = 0.0;
        this.estadoReserva = "";
    }


/*
    public Reserva(int numeroReserva, String nombre, LocalDate fechaEntrada, String numeroHabitacion, LocalDate fechaSalida, LocalDate fechaRegistro, double costoTotal) {

        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarObligatorio(fechaEntrada,SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA);
        validarObligatorio(numeroHabitacion,SE_DEBE_INGRESAR_EL_NUMERO_HABITACION);
        validarObligatorio(fechaSalida,SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA);
        validarObligatorio(fechaRegistro,SE_DEBE_INGRESAR_LA_FECHA_DE_REGISTRO);
        validarFechaEntrada(fechaEntrada,LA_FECHA_DE_ENTRADA_DEBE_SER_MAYOR_O_IGUAL_A_LA_ACTUAL);
        validarMenor(fechaEntrada,fechaSalida,LA_FECHA_DE_SALIDA_DEBE_SER_MAYOR);

        this.numeroReserva = numeroReserva;
        this.nombre = nombre;
        this.fechaEntrada = fechaEntrada;
        this.numeroHabitacion = numeroHabitacion;
        this.fechaSalida = fechaSalida;
        this.fechaRegistro = fechaRegistro;
        this.costoTotal = costoTotal;
    }*/
}
