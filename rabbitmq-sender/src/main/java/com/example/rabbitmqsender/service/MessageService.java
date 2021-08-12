package com.example.rabbitmqsender.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final RabbitTemplate rabbitTemplate;
    private final Queue durableQuorumQueue;
    private final Queue durableClassicQueue;
    private final Queue nonDurableQueue;

    public MessageService(RabbitTemplate rabbitTemplate,
                          @Qualifier("durable-quorum-queue") Queue durableQuorumQueue,
                          @Qualifier("durable-classic-queue") Queue durableClassicQueue,
                          @Qualifier("non-durable-queue") Queue nonDurableQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.durableQuorumQueue = durableQuorumQueue;
        this.durableClassicQueue = durableClassicQueue;
        this.nonDurableQueue = nonDurableQueue;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(durableQuorumQueue.getName(), message);
        rabbitTemplate.convertAndSend(durableClassicQueue.getName(), message);
        rabbitTemplate.convertAndSend(nonDurableQueue.getName(), message);
    }
}
