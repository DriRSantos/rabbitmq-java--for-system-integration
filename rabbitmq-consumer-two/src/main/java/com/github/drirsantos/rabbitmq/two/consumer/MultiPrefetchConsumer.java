package com.github.drirsantos.rabbitmq.two.consumer;

import com.github.drirsantos.rabbitmq.two.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class MultiPrefetchConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(MultiPrefetchConsumer.class);

    @RabbitListener(queues="q.transaction", concurrency = "2")
    public void listenTransation(DummyMessage message) throws InterruptedException {
        LOG.info("Taking transaction {}", message);
        TimeUnit.MILLISECONDS.sleep(100);
    }

    @RabbitListener(queues="q.scheduler", concurrency = "2", containerFactory = "prefetchOneContainerFactory")
    public void listenScheduler(DummyMessage message) throws InterruptedException {
        LOG.info("Taking scheduler {}", message);
        TimeUnit.MINUTES.sleep(1);
    }
}
