package com.dr.sattlers.bar.employee.waiter.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class WaiterController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static final Logger LOG = LoggerFactory.getLogger(WaiterController.class);

    @GetMapping("/welcome")
    public Welcome greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOG.info("Welcome {}! Your table number is {}", name ,  counter.incrementAndGet());
        return new Welcome(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/menu")
    public String menu() {
        return "Alcohol & Food";
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
}
