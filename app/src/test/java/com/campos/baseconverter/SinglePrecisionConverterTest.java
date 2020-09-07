package com.campos.baseconverter;

import com.campos.baseconverter.model.Base;
import com.campos.baseconverter.model.BaseNumber;
import com.campos.baseconverter.model.InvalidBaseNumberException;
import com.campos.baseconverter.model.SinglePrecisionConverter;

import org.junit.Test;

public class SinglePrecisionConverterTest {
    @Test
    public void convertToBinStrTest() throws InvalidBaseNumberException {
        BaseNumber number = new BaseNumber(Base.BASE_10, "15.125");
        SinglePrecisionConverter precisionConverter = new SinglePrecisionConverter(number);
        String result = precisionConverter.convertToBinStr();
        System.out.println("before: " + number.getValue());
        System.out.println("after: " + result);
    }

    @Test
    public void convertTest() throws InvalidBaseNumberException {
        BaseNumber number = new BaseNumber(Base.BASE_3, "1111.001");
        SinglePrecisionConverter precisionConverter = new SinglePrecisionConverter(number);
        precisionConverter.convertToDec();
    }
}
