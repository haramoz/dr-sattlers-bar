package com.dr.sattlers.bar.employee.waiter.service.rest;

import com.dr.sattlers.bar.config.KafkaConfigData;
import com.dr.sattlers.bar.employee.waiter.service.repository.TableRepository;
import com.dr.sattlers.bar.employee.waiter.service.model.Table;
import com.dr.sattlers.bar.infra.kafka.payload.OrderReceived;
import com.dr.sattlers.bar.infra.kafka.producer.service.impl.OrderReceivedKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@ComponentScan(basePackages = "com.dr.sattlers.bar")
public class WaiterController {
    private static final Logger LOG = LoggerFactory.getLogger(WaiterController.class);

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private long tableId;

    private final OrderReceivedKafkaProducer orderReceivedKafkaProducer;

    private final KafkaConfigData kafkaConfigData;

    private final String ORDER_RECEIVED = "order-received";
    private final String ORDER_DELIVERED = "order-delivered";

    private final String TABLE_FREE_STATUS = "available";
    private final String TABLE_NOT_FREE_STATUS = "occupied";

    @Autowired
    private TableRepository tableRepository;


    public WaiterController(OrderReceivedKafkaProducer orderReceivedKafkaProducer,
                            KafkaConfigData kafkaConfigData) {
        this.orderReceivedKafkaProducer = orderReceivedKafkaProducer;
        this.kafkaConfigData = kafkaConfigData;
    }

    @GetMapping("/welcome")
    public Welcome greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOG.info("Welcome {}! Your table number is {}", name ,  counter.incrementAndGet());
        return new Welcome(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/menu")
    public String menu() {
        return "Alcohol & Food & more alcohol";
    }


    /**
    * Returns ETA in minutes
    * @RequestParam: String TableId.
    *  **/
    @GetMapping("/etaFood")
    public int etaFood(@RequestParam(value = "table",
            defaultValue = "1") String tableId) {
        //TODO tableID based return
        Random rand = new Random();
        return rand.nextInt(20);
    }

    @GetMapping("/suggestFood")
    public List<String> suggestFood() {
        // TODO suggest according to vegan, Vegetarian and non-veg
        return new ArrayList<String>(Arrays.asList("Steak", "Pommes", "Alt beer"));
    }

    @PostMapping("/takeOrders")
    public boolean takeOrders(
            @RequestParam(value = "table", defaultValue = "1")
            String tableId) {

        //TODO process the order with database & Event
        OrderReceived orderReceived = new OrderReceived();
        orderReceived.setTableId(String.valueOf(getTableId()));
        orderReceived.setFood("Steak");
        orderReceived.setDrinks("AltBier");


        orderReceivedKafkaProducer.send(
                ORDER_RECEIVED,
                getTableId() ,
                orderReceived);
        return true;
    }

    @PostMapping("/initiatePayment")
    public boolean initiatePayment(
            @RequestParam(value = "table", defaultValue = "1")
                                       String tableId,
            @RequestParam(value = "amount")
                                       String amount) {
        // TODO process payment via external service API
        return true;
    }

    @GetMapping("/findtable")
    public String findTable() {
        List<Table> freeTables = tableRepository.findByStatus(TABLE_FREE_STATUS);
        if (freeTables.isEmpty()) {
            return "Sorry, there are no free tables at the moment.";
        } else {
            Table table = freeTables.get(0);
            table.setStatus(TABLE_NOT_FREE_STATUS);
            tableRepository.save(table);
            return "Your table number is " + table.getTableId();
        }
    }

    // Setter Getters TODO what happened to Lombok?

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }
}
