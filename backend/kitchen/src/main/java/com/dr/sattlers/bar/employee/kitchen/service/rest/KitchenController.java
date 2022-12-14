package com.dr.sattlers.bar.employee.kitchen.service.rest;

import com.dr.sattlers.bar.config.KafkaConfigData;
import com.dr.sattlers.bar.infra.kafka.producer.service.impl.OrderDeliveredKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan(basePackages = "com.dr.sattlers.bar")
public class KitchenController {

    private static final Logger LOG = LoggerFactory.getLogger(KitchenController.class);

    private final OrderDeliveredKafkaProducer orderReceivedKafkaProducer;

    private final KafkaConfigData kafkaConfigData;

    //private final OrderDeliveredKafkaConsumer orderDeliveredKafkaConsumer;


    public KitchenController(OrderDeliveredKafkaProducer orderReceivedKafkaProducer,
                             KafkaConfigData kafkaConfigData
                             ) {
        this.orderReceivedKafkaProducer = orderReceivedKafkaProducer;
        this.kafkaConfigData = kafkaConfigData;
    }

    @GetMapping("/testKitchen")
    public String test() {
        return "Kitchen is Open";
    }

}
