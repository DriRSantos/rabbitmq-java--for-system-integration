package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class RetryImageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RetryImageConsumer.class);
    private static final String DEAD_EXCHANGE_NAME = "x.guideline.dead";
    private DlxProcessingErrorHandler dlxProcessingErrorHandler;

    @Autowired
    private ObjectMapper objectMapper;

    public RetryImageConsumer() {
        this.dlxProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
    }

    @RabbitListener(queues = "q.guideline.image.work")
    public void listen(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)
            throws InterruptedException, JsonParseException, JsonMappingException, IOException {
        try {
            var p = objectMapper.readValue(message.getBody(), Picture.class);
            if (p.getSize() > 9000) {
                throw new IOException("Size too large");
            } else {
                LOG.info("Creating thumbnail & publishing : " + p);
                channel.basicAck(deliveryTag, false);
            }
        } catch (IOException e) {
            LOG.warn("Error processing message : " + new String(message.getBody()) + " : " + e.getMessage());
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel, deliveryTag);
        }
    }

}
