package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

//@Service
public class PictureImageConsumer {

    private static final Logger log = LoggerFactory.getLogger(PictureImageConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.picture.image")
    public void listen(String message) throws IOException {
        Picture picture = objectMapper.readValue(message, Picture.class);
        log.info("On image : {} ", picture);
    }
}
