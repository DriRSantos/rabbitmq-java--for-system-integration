package com.course.rabbitmq.producer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitmqQueue {

    private Long messages;
    private String names;

    public Long getMessages() {
        return messages;
    }

    public void setMessages(Long messages) {
        this.messages = messages;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "RabbitmqQueue{" +
                "messages=" + messages +
                ", names='" + names + '\'' +
                '}';
    }

    public Boolean isDirty(){
        return messages > 0;
    }
}
