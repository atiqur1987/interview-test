package com.travix.medusa.busyflights.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;

public abstract class RegisterConverter<S, T> implements Converter<S, T> {

    private final ConversionService conversionService;

    public RegisterConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
        ((ConverterRegistry) conversionService).addConverter(this);
    }

    protected <T> T convert(Object obj, Class<T> clazz) {
        return this.conversionService.convert(obj, clazz);
    }
}
