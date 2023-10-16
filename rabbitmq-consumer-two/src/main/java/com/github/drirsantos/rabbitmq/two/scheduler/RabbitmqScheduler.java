package com.github.drirsantos.rabbitmq.two.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class RabbitmqScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitmqScheduler.class);

    @Autowired
    private RabbitListenerEndpointRegistry registry;

    @Scheduled(cron = "0 33 20 * * *")
    public void stopAll(){
        registry.getListenerContainers().forEach(container -> {
            LOG.info("Stopping listener container {}", container);
            container.stop();
        });
    }
    @Scheduled(cron = "0 37 20 * * *")
    public void startAll(){
        registry.getListenerContainers().forEach(container -> {
            LOG.info("Start listener container {}", container);
            container.start();
        });
    }
}
