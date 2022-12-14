package com.dr.sattlers.bar.infra.kafka.consumer.service.impl;

import com.dr.sattlers.bar.config.KafkaConfigData;
import com.dr.sattlers.bar.infra.kafka.admin.client.KafkaAdminClient;
import com.dr.sattlers.bar.infra.kafka.consumer.service.KafkaConsumer;
import com.dr.sattlers.bar.infra.kafka.payload.OrderReceived;
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
public class OrderReceivedKafkaConsumer implements KafkaConsumer<OrderReceived> {
    private static final Logger LOG = LoggerFactory.getLogger(OrderReceivedKafkaConsumer.class);

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private final KafkaAdminClient kafkaAdminClient;

    private final KafkaConfigData kafkaConfigData;

    public OrderReceivedKafkaConsumer(KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry,
                                      KafkaAdminClient kafkaAdminClient,
                                      KafkaConfigData kafkaConfigData) {
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
        this.kafkaAdminClient = kafkaAdminClient;
        this.kafkaConfigData = kafkaConfigData;
    }

    @Override
    @KafkaListener(id = "OrderReceivedTopicListener", topics = "${kafka-config.topic-name}")
    public void receive(@Payload List<OrderReceived> messages) {
        LOG.info("inside kafka consumer Arkkkkkkaaaa!! OrderReceivedTopicListener");
        LOG.info("{} number of message received with Thread id {}",
                messages.size(),
                Thread.currentThread().getId());
        // TODO process the consumer data
        //List<OrderReceived> twitterIndexModels = avroToElasticModelTransformer.getElasticModels(messages);
        //List<String> documentIds = elasticIndexClient.save(twitterIndexModels);
        //LOG.info("Documents saved to elasticsearch with ids {}", documentIds.toArray());
    }

    @EventListener
    public void onAppStarted(ApplicationStartedEvent event) {
        kafkaAdminClient.checkTopicsCreated();
        LOG.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
        kafkaListenerEndpointRegistry.getListenerContainer("OrderReceivedTopicListener").start();
    }
}
