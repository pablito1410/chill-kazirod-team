package com.chill.table.football.infrastructure.springconfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.annotation.PropertyAccessor.*;

@Configuration
public class JacksonConfiguration {

    @Autowired
    public void configureJackson(final ObjectMapper jackson2ObjectMapper) {
        jackson2ObjectMapper.setVisibility(ALL, JsonAutoDetect.Visibility.NONE);
        jackson2ObjectMapper.setVisibility(FIELD, JsonAutoDetect.Visibility.ANY);

        jackson2ObjectMapper.registerModule(new JavaTimeModule());

        jackson2ObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        jackson2ObjectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        jackson2ObjectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
    }
}
