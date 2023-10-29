package com.dev.alex.phonecollect.utils;


import com.dev.alex.phonecollect.model.Mts.MtsNumbersDTO;
import com.dev.alex.phonecollect.model.Mts.MtsRootDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonParser {

    public <T> T parseString(String jsonString, Class<T> clazz) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                // Включить пробелы и переносы строк для форматированного вывода
                // ObjectWriter writer = objectMapper.writer().with(SerializationFeature.INDENT_OUTPUT);
                if (clazz==null) { // todo убрать безобразие
                    return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, MtsNumbersDTO.class));
                }
                return objectMapper.readValue(jsonString, clazz);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
