package com.course.rabbitmq.producer.producer;

import com.course.rabbitmq.producer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducer2 {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Picture picture) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(picture);

        // creating routing key
        StringBuilder sb = new StringBuilder();
        // 1st word picture source
        sb.append(picture.getSource());
        sb.append(".");

        // 2nd word picture size
        if(picture.getSize() > 4000){
            sb.append("large");
        }
        else {
            sb.append("small");
        }
        sb.append(".");

        // 3rd word picture type
        sb.append(picture.getType());

        rabbitTemplate.convertAndSend("x.picture2", sb.toString(), json);
    }
}
