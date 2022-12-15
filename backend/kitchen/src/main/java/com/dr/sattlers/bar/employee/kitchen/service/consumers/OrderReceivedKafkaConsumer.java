package com.dr.sattlers.bar.employee.kitchen.service.consumers;

import com.dr.sattlers.bar.config.KafkaConfigData;
import com.dr.sattlers.bar.infra.kafka.admin.client.KafkaAdminClient;
import com.dr.sattlers.bar.infra.kafka.admin.exception.KafkaClientException;
import com.dr.sattlers.bar.infra.kafka.consumer.service.KafkaConsumer;
import com.dr.sattlers.bar.infra.kafka.payload.OrderDelivered;
import com.dr.sattlers.bar.infra.kafka.payload.OrderReceived;
import com.dr.sattlers.bar.infra.kafka.producer.service.impl.OrderDeliveredKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dr.sattlers.bar.employee.kitchen.service.utils.Utils.ORDER_DELIVERED;

@Service
public class OrderReceivedKafkaConsumer implements KafkaConsumer<OrderReceived> {
    private static final Logger LOG = LoggerFactory.getLogger(OrderReceivedKafkaConsumer.class);

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final OrderDeliveredKafkaProducer orderDeliveredKafkaProducer;
    private final KafkaAdminClient kafkaAdminClient;

    private final KafkaConfigData kafkaConfigData;

    public OrderReceivedKafkaConsumer(KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry,
                                      OrderDeliveredKafkaProducer orderDeliveredKafkaProducer,
                                      KafkaAdminClient kafkaAdminClient,
                                      KafkaConfigData kafkaConfigData) {
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
        this.orderDeliveredKafkaProducer = orderDeliveredKafkaProducer;
        this.kafkaAdminClient = kafkaAdminClient;
        this.kafkaConfigData = kafkaConfigData;
    }

    @Override
    @KafkaListener(id = "OrderReceivedTopicListener", topics = "order-received")
    public void receive(@Payload List<OrderReceived> messages) {
        LOG.info(messages.get(0).getFood());
        LOG.info("Kitchen-service: inside kafka consumer OrderReceivedTopicListener");
        LOG.info("{} number of message received with Thread id {}",
                messages.size(),
                Thread.currentThread().getId());
        for (var message : messages) {
            try {
                prepareFood(message);
            } catch (InterruptedException e) {
                throw new KafkaClientException("Failed for " +
                        "process order for table: " +
                        message.getTableId());
            }
        }
    }

    public void prepareFood(OrderReceived order) throws InterruptedException {
        LOG.info("Preparing for table {} food {} and drinks {}",
                order.getTableId(), order.getFood(), order.getDrinks());


        OrderDelivered orderDelivered = new OrderDelivered();
        orderDelivered.setTableId(String.valueOf(order.getTableId()));
        orderDelivered.setFood(order.getFood());
        orderDelivered.setDrinks(order.getDrinks());

        var processingTime = Math.random() * 1000;
        Thread.sleep((long)(processingTime));

        LOG.info("Order is ready for pickup for table {} food {} and drinks {} ready after {} ms",
                order.getTableId(), order.getFood(), order.getDrinks(), processingTime);

        orderDeliveredKafkaProducer.send(ORDER_DELIVERED,
                Long.valueOf(order.getTableId()),
                orderDelivered );

        LOG.info("Order ready for delivery notification has been sent!");
    }

    @EventListener
    public void onAppStarted(ApplicationStartedEvent event) {
        kafkaAdminClient.checkTopicsCreated();
        LOG.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
        kafkaListenerEndpointRegistry.getListenerContainer("OrderReceivedTopicListener").start();
    }
}
