package com.mauriciocogo.tcc_backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauriciocogo.tcc_backend.dto.OperatingHours;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonConverter implements AttributeConverter<OperatingHours, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(OperatingHours attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Erro convertendo para JSON", e);
        }
    }

    @Override
    public OperatingHours convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, OperatingHours.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro convertendo JSON para objeto", e);
        }
    }
}
