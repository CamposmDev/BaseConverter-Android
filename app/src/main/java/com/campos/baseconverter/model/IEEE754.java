package com.campos.baseconverter.model;

import java.util.Arrays;

public class IEEE754 {

    public String toSinglePrecision(BaseNumber input) {
        String result = "";
        if (input.getBase().equals(Base.BASE_2)) {
            String value = input.getValue();
            formatBinStr(value);
        }
        return result;
    }

    /**
     * result[0] contains value, result[1] contains power
     *
     * @return result[]
     */
    private String[] formatBinStr(String value) {
        StringBuilder sb = new StringBuilder(value);
        int posDot = sb.indexOf(".");
        int posOne = sb.indexOf("1");
        int x = posDot - posOne;

        System.out.println("x=" + x);
        System.out.println(sb);
        System.out.println("posDot=" + posDot);
        System.out.println("posOne=" + posOne);
        if (x == 1) {
            System.out.println("Leave the decimal");
        } else if (x > 1) {
            System.out.println("Move decimal to the right");
            sb.insert(posOne + 1, '.');
            sb.deleteCharAt(posDot + 1);
            posDot = sb.indexOf(".");
        } else {
            System.out.println("Move decimal to the left");
            sb.insert(posOne + 1, '.');
            sb.deleteCharAt(posDot);
            posDot = sb.indexOf(".");
        }
        sb = sb.delete(0, posDot - 1);
        System.out.println(sb);
        return new String[] {sb.toString()};
    }

    private boolean containsFractions(String value) {
        return value.contains("[.]");
    }
}
