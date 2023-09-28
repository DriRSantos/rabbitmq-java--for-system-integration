package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

//@Service
public class MarketingConsumer {

    private static final Logger log = LoggerFactory.getLogger(MarketingConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.hr.marketing")
    public void listen(String message) throws IOException {
        Employee employee = objectMapper.readValue(message, Employee.class);
        log.info("Log Employee on marketing is {} ", employee);
    }
}
