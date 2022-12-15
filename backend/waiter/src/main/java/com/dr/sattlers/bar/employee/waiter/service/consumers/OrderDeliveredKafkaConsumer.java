package com.dr.sattlers.bar.employee.waiter.service.consumers;

import com.dr.sattlers.bar.config.KafkaConfigData;
import com.dr.sattlers.bar.infra.kafka.admin.client.KafkaAdminClient;
import com.dr.sattlers.bar.infra.kafka.consumer.service.KafkaConsumer;
import com.dr.sattlers.bar.infra.kafka.payload.OrderDelivered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDeliveredKafkaConsumer implements KafkaConsumer<OrderDelivered> {
    private static final Logger LOG = LoggerFactory.getLogger(OrderDeliveredKafkaConsumer.class);

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private final KafkaAdminClient kafkaAdminClient;

    private final KafkaConfigData kafkaConfigData;

    public OrderDeliveredKafkaConsumer(KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry,
                                       KafkaAdminClient kafkaAdminClient,
                                       KafkaConfigData kafkaConfigData) {
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
        this.kafkaAdminClient = kafkaAdminClient;
        this.kafkaConfigData = kafkaConfigData;
    }

    @Override
    @KafkaListener(id = "OrderDeliveredTopicListener", topics = "${kafka-config.topic-name}")
    public void receive(@Payload List<OrderDelivered> messages) {
        LOG.info("inside kafka consumer OrderDeliveredTopicListener");
        LOG.info("{} number of message received with Thread id {}",
                messages.size(),
                Thread.currentThread().getId());
        // TODO process the consumer data
        //List<TwitterIndexModel> twitterIndexModels = avroToElasticModelTransformer.getElasticModels(messages);
    }

    @EventListener
    public void onAppStarted(ApplicationStartedEvent event) {
        kafkaAdminClient.checkTopicsCreated();
        LOG.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
        kafkaListenerEndpointRegistry.getListenerContainer("OrderDeliveredTopicListener").start();
    }
}
