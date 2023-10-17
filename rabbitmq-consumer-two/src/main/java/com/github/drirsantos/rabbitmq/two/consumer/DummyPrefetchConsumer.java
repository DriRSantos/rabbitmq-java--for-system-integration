package com.github.drirsantos.rabbitmq.two.consumer;

import com.github.drirsantos.rabbitmq.two.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.TimeoutTask;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class DummyPrefetchConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DummyPrefetchConsumer.class);

    @RabbitListener(queues = "q.dummy", concurrency = "2")
    public void listenDummy(DummyMessage message) throws InterruptedException {
        LOG.info("Message received {}", message);
        TimeUnit.SECONDS.sleep(20);
    }
}
