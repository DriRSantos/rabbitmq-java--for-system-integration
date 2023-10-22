package com.github.drirsantos.rabbitmq.two.producer;

import com.github.drirsantos.rabbitmq.two.entity.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class DummyProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendDummy(DummyMessage message){
        rabbitTemplate.convertAndSend("x.dummy", "", message);
    }
}
