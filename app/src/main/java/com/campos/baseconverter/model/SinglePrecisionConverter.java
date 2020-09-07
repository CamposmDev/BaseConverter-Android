package com.campos.baseconverter.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SinglePrecisionConverter {
    private BaseNumber input;

    public SinglePrecisionConverter(BaseNumber input) {
        this.input = input;
    }

    public BaseNumber getInput() {
        return input;
    }

    public String convertToBinStr() throws InvalidBaseNumberException {
        String result = "";
        if (input.getBase().equals(Base.BASE_10)) {
            BigDecimal value = new BigDecimal(input.getValue());
            BigDecimal[] arr = value.divideAndRemainder(BigDecimal.ONE);

            BaseNumber num = new BaseNumber(input.getBase(), arr[0].toBigInteger().toString());
            BaseConverter baseConverter = new BaseConverter(num, Base.BASE_2);
            BaseNumber result1 = baseConverter.convert(); //  Result before '.'

            BigDecimal decimals = arr[1];
            String result2 = computeDecimals(decimals);
            result = result1.getValue() + "." + result2;
        }
        return result;
    }

    private String computeDecimals(BigDecimal decimals) {
        String result = "";
        final int RADIX = Base.BASE_2.getRadix();
        final int LIMIT = 23;
        for (int i = 0 ;i < LIMIT; i++) {
            decimals = decimals.multiply(BigDecimal.valueOf(RADIX));
            BigInteger bit = decimals.divide(BigDecimal.ONE).toBigInteger();
            if (BigDecimal.ONE.compareTo(decimals) <= 0) { // Is greater than 1
                decimals = decimals.subtract(new BigDecimal(bit));
                result = result + bit.toString();
            } else { // Is less than one
                result = result + bit.toString();
            }
        }

        return result;
    }
}
