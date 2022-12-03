package com.dr.sattlers.bar.infra.kafka.producer.service.impl;

import com.dr.sattlers.bar.infra.kafka.payload.OrderDelivered;
import com.dr.sattlers.bar.infra.kafka.producer.service.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PreDestroy;

@Service
public class OrderDeliveredKafkaProducer implements KafkaProducer<Long, OrderDelivered> {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDeliveredKafkaProducer.class);

    private KafkaTemplate<Long, OrderDelivered> kafkaTemplate;

    public OrderDeliveredKafkaProducer(KafkaTemplate<Long, OrderDelivered> template) {
        this.kafkaTemplate = template;
    }

    @Override
    public void send(String topicName, Long key, OrderDelivered message) {
        LOG.info("Sending message='{}' to topic='{}'", message, topicName);
        ListenableFuture<SendResult<Long, OrderDelivered>> kafkaResultFuture =
                kafkaTemplate.send(topicName, key, message);
        addCallback(topicName, message, kafkaResultFuture);
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            LOG.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }

    private void addCallback(String topicName, OrderDelivered message,
                             ListenableFuture<SendResult<Long, OrderDelivered>> kafkaResultFuture) {
        kafkaResultFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
            }

            @Override
            public void onSuccess(SendResult<Long, OrderDelivered> result) {
                    RecordMetadata metadata = result.getRecordMetadata();
                    LOG.debug("Received new metadata. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                            metadata.topic(),
                            metadata.partition(),
                            metadata.offset(),
                            metadata.timestamp(),
                            System.nanoTime());
            }
        });
    }
}
