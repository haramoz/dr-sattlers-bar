package com.dr.sattlers.bar.employee.waiter;

import com.dr.sattlers.bar.config.KafkaConfigData;
import com.dr.sattlers.bar.employee.waiter.service.exception.WaiterServiceException;
import com.dr.sattlers.bar.infra.kafka.payload.OrderReceived;
import com.dr.sattlers.bar.infra.kafka.producer.service.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dr.sattlers.bar")
public class WaiterApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(WaiterApplication.class);
	private final KafkaConfigData kafkaConfigData;

	private final KafkaProducer<Long, OrderReceived> kafkaProducer;

	public WaiterApplication(KafkaConfigData kafkaConfigData,
							 KafkaProducer<Long, OrderReceived> kafkaProducer) {
		this.kafkaConfigData = kafkaConfigData;
		this.kafkaProducer = kafkaProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(WaiterApplication.class, args);
	}

	@Override
	public void run(String... args) throws WaiterServiceException {
		LOG.info("Waiter service instance is starting ...");
	}
}
