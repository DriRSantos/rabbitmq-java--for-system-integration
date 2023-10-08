package com.course.rabbitmq.producer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FixedRateProducer {

    private static final Logger LOG = LoggerFactory.getLogger(FixedRateProducer.class);
    @Autowired
    public RabbitTemplate rabbitTemplate;
    private Integer i = 0;

    @Scheduled(fixedRate = 500)
    public void sendMessageToRabbit() {
        i++;
        LOG.info("i is { } = " + i);
        rabbitTemplate.convertAndSend("course.fixedrate", "Fieed rate" + i);
    }
}
