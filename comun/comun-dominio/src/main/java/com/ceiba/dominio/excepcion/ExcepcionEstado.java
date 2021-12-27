package com.ceiba.dominio.excepcion;

public class ExcepcionEstado extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public ExcepcionEstado(String message) {
        super(message);
    }
}
