package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.dto.DtoReservaCobro;

import static com.ceiba.dominio.formato.FormatoDouble.darFormatoDosDecimales;

public class ServicioCobrarReserva {

    public void ejecutar(DtoReservaCobro reservaCobro) {
        double costoTotalDolares = reservaCobro.getCostoTotalPesos() / reservaCobro.getTrm();
        reservaCobro.setCostoTotalDolares(darFormatoDosDecimales(costoTotalDolares));
    }
}
