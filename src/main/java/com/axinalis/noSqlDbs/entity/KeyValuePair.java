package com.axinalis.noSqlDbs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@RedisHash("KeyValue")
public class KeyValuePair {

    @Id
    private String keyValue;
    private String valueValue;

    public KeyValuePair() {
    }

    public KeyValuePair(String keyValue, String valueValue) {
        this.keyValue = keyValue;
        this.valueValue = valueValue;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getValueValue() {
        return valueValue;
    }

    public void setValueValue(String valueValue) {
        this.valueValue = valueValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyValuePair that = (KeyValuePair) o;
        return Objects.equals(keyValue, that.keyValue) && Objects.equals(valueValue, that.valueValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyValue, valueValue);
    }

    @Override
    public String toString() {
        return "KeyValuePair{" +
                "keyValue='" + keyValue + '\'' +
                ", valueValue='" + valueValue + '\'' +
                '}';
    }
}
