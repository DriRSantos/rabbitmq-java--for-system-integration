package com.github.drirsantos.rabbitmq.two;

import com.github.drirsantos.rabbitmq.two.entity.DummyMessage;
import com.github.drirsantos.rabbitmq.two.producer.DummyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;

@SpringBootApplication
public class RabbitmqProducerTwoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerTwoApplication.class, args);
	}

	@Autowired
	private DummyProducer dummyProducer;

	@Override
	public void run(String... args) throws Exception {
		var dummyMessage = new DummyMessage("Now is " + LocalTime.now(), 1);
		dummyProducer.sendDummy(dummyMessage);
	}
}
