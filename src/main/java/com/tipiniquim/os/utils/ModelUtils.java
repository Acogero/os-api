package com.tipiniquim.os.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    private ModelUtils() {
    }

    public static <T> List<T> convertModelList(List<? extends Object> modelList, Class<T> destinationType, PropertyMap<? extends Object, ? extends Object> propertyMap) {
        if (modelList == null || destinationType == null) {
            throw new IllegalArgumentException();
        }
        if (modelList.isEmpty()) {
            return new ArrayList<>();
        }
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (propertyMap != null) {
            modelMapper.addMappings(propertyMap);
        }
        return modelList.stream().map(model -> modelMapper.map(model, destinationType)).collect(Collectors.toList());
    }

    public static <T> List<T> convertModelList(List<? extends Object> modelList, Class<T> destinationType) {
        if (modelList == null || destinationType == null) {
            throw new IllegalArgumentException();
        }
        if (modelList.isEmpty()) {
            return new ArrayList<>();
        }
        return modelList.stream().map(model -> modelMapper.map(model, destinationType)).collect(Collectors.toList());
    }

    public static <T> T convertModel(Object model, Class<T> destinationType) {
        if (model == null || destinationType == null) {
            return null;
        }
        return modelMapper.map(model, destinationType);
    }

    public static <T> T convertModel(Object model, Class<T> destinationType, PropertyMap<? extends Object, ? extends Object> propertyMap) {
        if (model == null || destinationType == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);
        return modelMapper.map(model, destinationType);
    }

    public static <T> T readValue(String model, Class<T> destinationType) {
        validarCamposObrigatorios(model, destinationType);

        final ObjectMapper modelMapper = new ObjectMapper();
        modelMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return modelMapper.readValue(model, destinationType);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static <T> void validarCamposObrigatorios(Object model, Class<T> destinationType) {
        if (model == null || destinationType == null) {
            throw new IllegalArgumentException();
        }
    }
}
