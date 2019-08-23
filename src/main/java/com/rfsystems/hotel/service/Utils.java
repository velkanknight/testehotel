package com.rfsystems.hotel.service;

import java.math.BigDecimal;

public class Utils {

    public static Double calcularComissao(Double price){
        return price / 0.7;
    }

    public static double truncate(Double valor) {
        BigDecimal bd = BigDecimal.valueOf(valor);
        bd = bd.setScale(2, BigDecimal.ROUND_DOWN);

        return bd.doubleValue();
    }
}
