package com.vieeo.util.number;

import java.math.BigDecimal;

public class AmountUtils {

    public static Long getLongValue(BigDecimal amount){
        if(amount == null) return null;
        return amount.multiply(new BigDecimal(1000)).longValue();
    }

    public static BigDecimal getBigDecimalValue(Long amount){
        if(amount == null) return null;
        return new BigDecimal(amount).divide(new BigDecimal(1000),BigDecimal.ROUND_FLOOR);
    }

}
