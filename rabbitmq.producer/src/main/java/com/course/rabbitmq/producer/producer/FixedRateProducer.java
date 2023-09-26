package com.course.rabbitmq.producer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class FixedRateProducer {

    @Autowired
    public RabbitTemplate rabbitTemplate;
    private Integer i = 0;
    private static final Logger log = LoggerFactory.getLogger(FixedRateProducer.class);

    @Scheduled(fixedRate = 500)
    public void sendMessageToRabbit(){
        i++;
//        log.info("i is { } = " + i);
        rabbitTemplate.convertAndSend("course.fixedrate", "Fieed rate" + i);
    }
}
