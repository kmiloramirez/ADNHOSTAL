package com.ceiba.habitacion.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Habitacion {

    private static final String LA_HABITACION_MINIMO_CAMA = "La habitacion debe tener como minimo: %s";
    private static final String LA_HABITACION_MAXIMA_CAMA = "La habitacion debe tener como maximo: %s";
    private static final String EL_PRECIO_DEBE_POSITIVO = "El precio de la habitacion debe ser positivo";
    private static final String SE_DEBE_INGRESAR_NUMERO_HABITACION = "Se debe agregar un numero de habitacion";
    private static final String SE_DEBE_INGRESAR_DESCRIPCION_HABITACION = "Se debe agregar un descripcion de habitacion";
    private static final int CAMAS_MINIMAS = 1;
    private static final int CAMAS_MAXIMAS = 3;

    private String numero;
    private int camas;
    private double precio;
    private String descripcion;

    public Habitacion(String numero, int camas, double precio, String descripcion) {

        validarObligatorio(numero, SE_DEBE_INGRESAR_NUMERO_HABITACION);
        validarNoVacio(numero, SE_DEBE_INGRESAR_NUMERO_HABITACION);
        validarObligatorio(descripcion, SE_DEBE_INGRESAR_DESCRIPCION_HABITACION);
        validarNoVacio(descripcion, SE_DEBE_INGRESAR_DESCRIPCION_HABITACION);
        validarPositivo(precio, EL_PRECIO_DEBE_POSITIVO);
        validarMenor(CAMAS_MINIMAS, camas, String.format(LA_HABITACION_MINIMO_CAMA, CAMAS_MINIMAS));
        validarMaximo(CAMAS_MAXIMAS, camas, String.format(LA_HABITACION_MAXIMA_CAMA, CAMAS_MAXIMAS));

        this.numero = numero;
        this.camas = camas;
        this.precio = precio;
        this.descripcion = descripcion;
    }

}
