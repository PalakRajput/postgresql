package com.example.demo.converter;

import com.example.demo.model.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class JSONAttributeConverter implements AttributeConverter<Address, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Address object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException jpe) {
            log.warn("Cannot convert Object into JSON");
            return null;
        }
    }

    @Override
    public Address convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, Address.class);
        } catch (JsonProcessingException e) {
            log.warn("Cannot convert JSON into Address");
            return null;
        }
    }
}
