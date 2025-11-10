package com.mauriciocogo.tcc_backend.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauriciocogo.tcc_backend.dto.OperatingHours;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonConverter implements AttributeConverter<OperatingHours, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(OperatingHours attribute) {
        try {
            return attribute == null ? null : objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Erro ao converter OperatingHours para JSON", e);
        }
    }

    @Override
    public OperatingHours convertToEntityAttribute(String dbData) {
        try {
            return dbData == null ? null : objectMapper.readValue(dbData, OperatingHours.class);
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao converter JSON para OperatingHours", e);
        }
    }
}
