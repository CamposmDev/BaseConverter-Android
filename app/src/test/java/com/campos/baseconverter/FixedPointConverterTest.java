package com.campos.baseconverter;

import com.campos.baseconverter.model.num.Base;
import com.campos.baseconverter.model.num.BaseNumber;
import com.campos.baseconverter.model.num.FixedPointConverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FixedPointConverterTest {
    @Test
    public void convertTest1() {
        String expectedValue = "1111.001";
        BaseNumber input = new BaseNumber(Base.BASE_10, "15.125");
        Base convertTo = Base.BASE_2;
        FixedPointConverter converter = new FixedPointConverter(input);
        converter.setScale(3);
        BaseNumber result = converter.convert(convertTo);
        assertEquals(expectedValue, result.getValue());
    }

    @Test
    public void convertTest2() {
        String expectedValue = "15.125";
        BaseNumber input = new BaseNumber(Base.BASE_2, "1111.001");
        Base convertTo = Base.BASE_10;
        FixedPointConverter converter = new FixedPointConverter(input);
        converter.setScale(3);
        BaseNumber result = converter.convert(convertTo);
        assertEquals(expectedValue, result.getValue());
    }

    @Test
    public void convertTest3() {
        String expectedValue = "0.0000";
        BaseNumber input = new BaseNumber(Base.BASE_36, "0.0000");
        Base convertTo = Base.BASE_2;
        FixedPointConverter converter = new FixedPointConverter(input);
        converter.setScale(4);
        BaseNumber result = converter.convert(convertTo);
        assertEquals(expectedValue, result.getValue());
    }
}
