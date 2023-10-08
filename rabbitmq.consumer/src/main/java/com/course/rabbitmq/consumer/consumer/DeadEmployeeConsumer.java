package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.Employee;
import com.course.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class DeadEmployeeConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DeadEmployeeConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.guideline2.accounting.dead", "q.guideline2.marketing.dead"})
    public void listen(String message) throws IOException {
         Employee employee = objectMapper.readValue(message, Employee.class);
         LOG.info("Employee dead exchange : {} " + employee);
    }
}