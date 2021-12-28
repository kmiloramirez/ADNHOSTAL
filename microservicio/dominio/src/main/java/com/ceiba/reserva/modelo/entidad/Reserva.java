package com.ceiba.reserva.modelo.entidad;

import com.ceiba.reserva.modelo.enumerador.EstadoReserva;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private static final String EL_NUMERO_RESERVA_DEBE_SER_POSITIVO = "El numero de reerva debe ser positivo";
    private static final String SE_DEBE_INGRESAR_EL_ESTADO = "Se debe ingresar un estado";
    private static final String SE_DEBE_INGRESAR_EL_ESTADO_VALIDO = "Se debe ingresar un estado valido";

    private int id;
    private String nombre;
    private LocalDateTime fechaEntrada;
    private String numeroHabitacion;
    private LocalDateTime fechaSalida;
    private LocalDate fechaRegistro;
    @Setter
    private double costoTotal;
    @Setter
    private String estadoReserva;

    public Reserva(String nombre, LocalDateTime fechaEntrada, String numeroHabitacion, LocalDateTime fechaSalida) {

        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarNoVacio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarObligatorio(fechaEntrada, SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA);
        validarObligatorio(numeroHabitacion, SE_DEBE_INGRESAR_EL_NUMERO_HABITACION);
        validarNoVacio(numeroHabitacion, SE_DEBE_INGRESAR_EL_NUMERO_HABITACION);
        validarObligatorio(fechaSalida, SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA);
        validarFechaEntrada(fechaEntrada, LA_FECHA_DE_ENTRADA_DEBE_SER_MAYOR_O_IGUAL_A_LA_ACTUAL);
        validarFechasReserva(fechaEntrada, fechaSalida, LA_FECHA_DE_SALIDA_DEBE_SER_MAYOR);
        fechaEntrada = setearHoraDeEntradaHanitacion(fechaEntrada);
        fechaSalida = setearHoraDeSalidaHanitacion(fechaSalida);

        this.id = 1;
        this.nombre = nombre;
        this.fechaEntrada = fechaEntrada;
        this.numeroHabitacion = numeroHabitacion;
        this.fechaSalida = fechaSalida;
        this.fechaRegistro = LocalDate.now();
        this.costoTotal = 0.0;
        this.estadoReserva = EstadoReserva.RESEVADO.getEstado();
    }

    public Reserva(int id, String nombre, String estadoReserva) {

        validarPositivo(id, EL_NUMERO_RESERVA_DEBE_SER_POSITIVO);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarNoVacio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE);
        validarObligatorio(estadoReserva, SE_DEBE_INGRESAR_EL_ESTADO);
        validarNoVacio(estadoReserva, SE_DEBE_INGRESAR_EL_ESTADO);
        validarContenidoLista(estadoReserva, EstadoReserva.getEstados(), SE_DEBE_INGRESAR_EL_ESTADO_VALIDO);


        this.id = id;
        this.nombre = nombre;
        this.estadoReserva = estadoReserva;
    }

    private LocalDateTime setearHoraDeEntradaHanitacion(LocalDateTime fechaEntrada) {
        return fechaEntrada.withHour(15).withMinute(0).withSecond(0).withNano(0);

    }

    private LocalDateTime setearHoraDeSalidaHanitacion(LocalDateTime fechaSalida) {
        return fechaSalida.withHour(12).withMinute(0).withSecond(0).withNano(0);
    }
}
