package com.dr.sattlers.bar.infra.kafka.consumer.service;

import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

public interface KafkaConsumer<T extends SpecificRecordBase> {
    void receive(List<T> messages, List<Long> keys,
                 List<Integer> partitions, List<Long> offsets);
}
