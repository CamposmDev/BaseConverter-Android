package com.campos.baseconverter.model;

public class BinaryOperator {
    public String toTwosComp(BaseNumber input) {
        StringBuilder sb = new StringBuilder();
        if (input.isValidAndIs(Base.BASE_2)) {

        }
        return sb.toString();
    }

    public char[] flipBits(char[] input) {
        char[] arr = input.clone();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == '0')
                arr[i] = '1';
            if (c == '1')
                arr[i] = '0';
        }
        return arr;
    }

    public char[] addOne(char[] input) {
        char[] arr = input.clone();
        for (int i = (arr.length - 1); i >= 0; i--) {
            char c = arr[i];
            if (c == '0') {
                arr[i] = '1';
                break;
            }
            if (c == '1') {
                arr[i] = '0';
            }
        }
        return arr;
    }
}