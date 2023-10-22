package com.github.drirsantos.rabbitmq.two;

import com.github.drirsantos.rabbitmq.two.producer.MultiplePrefetchProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqProducerTwoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerTwoApplication.class, args);
	}

	@Autowired
	private MultiplePrefetchProducer multiplePrefetchProducer;

	@Override
	public void run(String... args) throws Exception {
		multiplePrefetchProducer.simulateTransaction();
		multiplePrefetchProducer.simulateScheduler();
		System.out.println("Messagens sent to x.transaction and x.scheduler");
	}
}
