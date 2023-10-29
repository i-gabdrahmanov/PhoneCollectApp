package com.dev.alex.phonecollect.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    public <T> T parseString(String jsonString, Class<T> clazz) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                // Включить пробелы и переносы строк для форматированного вывода
                // ObjectWriter writer = objectMapper.writer().with(SerializationFeature.INDENT_OUTPUT);
                return objectMapper.readValue(jsonString, clazz);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
