package com.mathisdulieu.ticketing.notification.utils;

import org.apache.kafka.clients.producer.ProducerRecord;

public final class ProducerRecordBuilder<K, V> {
    private String topic;
    private K key;
    private V value;

    private ProducerRecordBuilder() {}

    public static ProducerRecordBuilder<String, String> record() {
        return new ProducerRecordBuilder<>();
    }

    public static <V> ProducerRecordBuilder<String, V> record(Class<V> value) {
        return new ProducerRecordBuilder<>();
    }

    public static <K, V> ProducerRecordBuilder<K, V> record(Class<K> key, Class<V> value) {
        return new ProducerRecordBuilder<>();
    }

    public ProducerRecordBuilder<K, V> topic(String topic) {
        this.topic = topic;
        return this;
    }

    public ProducerRecordBuilder<K, V> key(K key) {
        this.key = key;
        return this;
    }

    public ProducerRecordBuilder<K, V> value(V value) {
        this.value = value;
        return this;
    }

    public ProducerRecord<K, V> build() {
        return new ProducerRecord<>(this.topic, this.key, this.value);
    }


}
