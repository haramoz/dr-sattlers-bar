package com.dr.sattlers.bar.infra.kafka.producer.service.impl;

import com.dr.sattlers.bar.infra.kafka.payload.PaymentInitiated;
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
public class PaymentInitiatedKafkaProducer implements KafkaProducer<Long, PaymentInitiated> {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentInitiatedKafkaProducer.class);

    private KafkaTemplate<Long, PaymentInitiated> kafkaTemplate;

    public PaymentInitiatedKafkaProducer(KafkaTemplate<Long, PaymentInitiated> template) {
        this.kafkaTemplate = template;
    }

    @Override
    public void send(String topicName, Long key, PaymentInitiated message) {
        LOG.info("Sending message='{}' to topic='{}'", message, topicName);
        ListenableFuture<SendResult<Long, PaymentInitiated>> kafkaResultFuture =
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

    private void addCallback(String topicName, PaymentInitiated message,
                             ListenableFuture<SendResult<Long, PaymentInitiated>> kafkaResultFuture) {
        kafkaResultFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
            }

            @Override
            public void onSuccess(SendResult<Long, PaymentInitiated> result) {
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
