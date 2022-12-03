package com.dr.sattlers.bar.infra.kafka.producer.service.impl;

import com.dr.sattlers.bar.infra.kafka.payload.PaymentProcessed;
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
public class PaymentProcessedKafkaProducer implements KafkaProducer<Long, PaymentProcessed> {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentProcessedKafkaProducer.class);

    private KafkaTemplate<Long, PaymentProcessed> kafkaTemplate;

    public PaymentProcessedKafkaProducer(KafkaTemplate<Long, PaymentProcessed> template) {
        this.kafkaTemplate = template;
    }

    @Override
    public void send(String topicName, Long key, PaymentProcessed message) {
        LOG.info("Sending message='{}' to topic='{}'", message, topicName);
        ListenableFuture<SendResult<Long, PaymentProcessed>> kafkaResultFuture =
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

    private void addCallback(String topicName, PaymentProcessed message,
                             ListenableFuture<SendResult<Long, PaymentProcessed>> kafkaResultFuture) {
        kafkaResultFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
            }

            @Override
            public void onSuccess(SendResult<Long, PaymentProcessed> result) {
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
