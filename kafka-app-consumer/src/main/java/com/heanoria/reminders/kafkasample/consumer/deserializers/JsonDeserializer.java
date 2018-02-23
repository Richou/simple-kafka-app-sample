package com.heanoria.reminders.kafkasample.consumer.deserializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class JsonDeserializer<T extends Serializable> implements Deserializer<T> {

    private final ObjectMapper objectMapper;

    private final Class<T> toClazz;

    public JsonDeserializer(Class<T> toClazz) {
        this(new ObjectMapper(), toClazz);
    }

    public JsonDeserializer(ObjectMapper objectMapper, Class<T> toClazz) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        this.toClazz = toClazz;
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return this.objectMapper.readValue(data, toClazz);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {

    }
}