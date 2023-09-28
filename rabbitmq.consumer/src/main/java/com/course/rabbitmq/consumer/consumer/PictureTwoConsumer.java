package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PictureTwoConsumer {

    private static final Logger log = LoggerFactory.getLogger(PictureTwoConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.picture.image", "q.picture.filter", "q.picture.log", "q.picture.vector"})
    public void listen(Message messageAmqp) throws IOException {
        var jsonString = new String(messageAmqp.getBody());
        Picture picture = objectMapper.readValue(jsonString, Picture.class);
        log.info("Consuming picture : {} with routing key : {}", picture,
                messageAmqp.getMessageProperties().getReceivedRoutingKey());
    }
}
