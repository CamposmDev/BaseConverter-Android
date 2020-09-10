package com.campos.baseconverter.model;

import java.math.BigDecimal;

public class FloatingPointerConverter {
    private BaseNumber input;

    public FloatingPointerConverter(BaseNumber input) {
        this.input = input;
    }

    public BaseNumber convert(Base convertTo) throws InvalidBaseNumberException {
        int compare = input.getBase().compareTo(convertTo);
        if (compare == 0) { // Return deep copy of input
            System.out.println("Returning input");
            return BaseNumber.deepCopy(input);
        } else { // Convert to Decimal then to convertTo
            System.out.println("Converting to Base 10");
            String strDec = convertToDec();
            if (convertTo.equals(Base.BASE_10)) {
                System.out.println("Returning Decimal");
                return new BaseNumber(convertTo, strDec);
            } else {
                System.out.println("Returning result");
                return new BaseNumber(convertTo, convertToBase(strDec, convertTo));
            }
        }
    }

    public String convertToDec() throws InvalidBaseNumberException {
        BigDecimal result;
        String[] arr = input.getValue().split("[.]");
        BaseNumber wholePart = calcWholePart(arr[0], Base.BASE_10);
        BigDecimal fractionalPart = calcFractionPartToDec(arr[1]);
        result = new BigDecimal(wholePart.getValue());
        result = result.add(fractionalPart);
        return result.toString();
    }

    private BaseNumber calcWholePart(String value, Base convertTo) throws InvalidBaseNumberException {
        BaseNumber num = new BaseNumber(input.getBase(), value);
        BaseConverter baseConverter = new BaseConverter(num, convertTo);
        return baseConverter.convert();
    }

    private BigDecimal calcFractionPartToDec(String s) {
        int pow = -1;
        double sum = 0;
        for (char c : s.toCharArray()) {
            double value;
            if (Character.isLetter(c)) {
                value = (c - 55);
            } else {
                value = Integer.valueOf(c + "");
            }
            sum += (value * Math.pow(input.getBase().getRadix(), pow--));
        }
        return new BigDecimal(sum);
    }

    private String convertToBase(String strDec, Base convertTo) throws  InvalidBaseNumberException {
        BigDecimal value = new BigDecimal(strDec);
        BigDecimal[] arr = value.divideAndRemainder(BigDecimal.ONE);
        String strWholePart = arr[0].toBigInteger().toString();
        String strFractionPart = calcFractionPartToBase(arr[1], convertTo);
        BaseNumber wholePart = calcWholePart(strWholePart, convertTo);
        return wholePart.getValue() + '.' + strFractionPart;
    }

    private String calcFractionPartToBase(BigDecimal value, Base convertTo) {
        StringBuilder result = new StringBuilder();
        final BigDecimal RADIX = new BigDecimal(convertTo.getRadix());
        final int LIMIT = 23; // I can make this a parameter
        for (int i = 0; i < LIMIT; i++) {
            value = value.multiply(RADIX);
            int wholePart = value.divide(BigDecimal.ONE).intValue();
            if (BigDecimal.ONE.compareTo(value) <= 0) { // Is whole part greater than 1
                value = value.subtract(new BigDecimal(wholePart));
                if (wholePart >= 10) {
                    result.append(((char)(wholePart + 55)));
                } else {
                    result.append(wholePart);
                }
            } else {
                result.append(wholePart);
            }
        }
        return result.toString();
    }

//    private String calcFractionPart(BigDecimal fractionalPart) {
//        String result = "";
//        final int RADIX = Base.BASE_2.getRadix();
//        final int LIMIT = 23;
//        for (int i = 0; i < LIMIT; i++) {
//            fractionalPart = fractionalPart.multiply(BigDecimal.valueOf(RADIX));
//            BigInteger wholePart = fractionalPart.divide(BigDecimal.ONE).toBigInteger();
//            if (BigDecimal.ONE.compareTo(fractionalPart) <= 0) { // Is greater than 1
//                fractionalPart = fractionalPart.subtract(new BigDecimal(wholePart));
//                result = result + wholePart.toString();
//            } else { // Is less than one
//                result = result + wholePart.toString();
//            }
//        }
//        return result;
//    }

//    public String convertToBinStr() throws InvalidBaseNumberException {
//        String result = "";
//        if (input.getBase().equals(Base.BASE_10)) {
//            BigDecimal value = new BigDecimal(input.getValue());
//            BigDecimal[] arr = value.divideAndRemainder(BigDecimal.ONE);
//
//            String wholePartValue = arr[0].toBigInteger().toString();
//            BaseNumber wholePart = calcWholePart(wholePartValue, Base.BASE_2);
//            String fractionalPart = calcFractionPart(arr[1]);
//            result = wholePart.getValue() + "." + fractionalPart;
//        }
//        return result;
//    }
}