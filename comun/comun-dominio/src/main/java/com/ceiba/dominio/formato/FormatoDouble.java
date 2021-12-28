package com.ceiba.dominio.formato;

import java.text.DecimalFormat;

public class FormatoDouble {
    private static final String FORMATO_DOS_DECIMALES ="#.##";
    public static double darFormatoDosDecimales(double valor){
        DecimalFormat df = new DecimalFormat(FORMATO_DOS_DECIMALES);
        return Double.parseDouble(df.format(valor));
    }
}
