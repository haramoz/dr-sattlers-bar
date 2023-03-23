package com.dr.sattlers.bar.employee.kitchen.service.rest;

import com.dr.sattlers.bar.config.KafkaConfigData;
import com.dr.sattlers.bar.employee.kitchen.service.utils.Utils;
import com.dr.sattlers.bar.infra.kafka.payload.OrderDelivered;
import com.dr.sattlers.bar.infra.kafka.producer.service.impl.OrderDeliveredKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dr.sattlers.bar.employee.kitchen.service.utils.Utils.ORDER_DELIVERED;

@RestController
@ComponentScan(basePackages = "com.dr.sattlers.bar")
public class KitchenController {
    private static final Logger LOG = LoggerFactory.getLogger(KitchenController.class);

    private final OrderDeliveredKafkaProducer orderReceivedKafkaProducer;

    private final KafkaConfigData kafkaConfigData;




    public KitchenController(OrderDeliveredKafkaProducer orderReceivedKafkaProducer,
                             KafkaConfigData kafkaConfigData
                             ) {
        this.orderReceivedKafkaProducer = orderReceivedKafkaProducer;
        this.kafkaConfigData = kafkaConfigData;
    }

    @GetMapping("/isKitchenOpen")
    public String isKitchen() {
        LOG.info("Kitchen is Open!");
        return "Kitchen is Open";
    }

    @GetMapping("/test")
    public String test(
            @RequestParam(value = "table", defaultValue = "1")
                           String tableId) {
        LOG.info("Received GET request {} sending to kafka topic {}", "test", ORDER_DELIVERED);
        OrderDelivered orderDelivered = new OrderDelivered();
        orderDelivered.setTableId(String.valueOf(tableId));
        orderDelivered.setFood("pasta");
        orderDelivered.setDrinks("mojito");
        LOG.info(kafkaConfigData.getTopicName());
        orderReceivedKafkaProducer.send(ORDER_DELIVERED, Long.valueOf(tableId), orderDelivered );
        return "Alcohol & Food & more alcohol";
    }

    @GetMapping("/orderstatus")
    public List<Order> orderStatus(
            @RequestParam(value = "table", defaultValue = "1")
            String tableId) {
        LOG.info("Received GET request {} sending to kafka topic {}", "orderStatus", ORDER_DELIVERED);
        //
        return "Alcohol & Food & more alcohol";
    }

}
