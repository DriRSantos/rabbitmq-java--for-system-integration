package com.github.drirsantos.rabbitmq.two.entity;

public class DummyMessage {

    private String content;
    private Integer publishOrder;

    public DummyMessage() {
    }
    public DummyMessage(String content, Integer publishOrder) {
        this.content = content;
        this.publishOrder = publishOrder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPublishOrder() {
        return publishOrder;
    }

    public void setPublishOrder(Integer publishOrder) {
        this.publishOrder = publishOrder;
    }

    @Override
    public String toString() {
        return "DummyMessage{" +
                "content='" + content + '\'' +
                ", publishOrder=" + publishOrder +
                '}';
    }
}
