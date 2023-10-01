package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class DeadImageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DeadImageConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.guideline.image.dead", "q.guideline.vector.dead"})
    public void listen(String message) throws IOException {
         Picture image = objectMapper.readValue(message, Picture.class);
         LOG.info("Image dead exchange : {} " + image);
    }
}