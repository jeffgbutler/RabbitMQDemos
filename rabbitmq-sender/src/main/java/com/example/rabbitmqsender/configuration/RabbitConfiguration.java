package com.example.rabbitmqsender.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean(name = "durable-quorum-queue")
    Queue durableQuorumQueue() {
        return QueueBuilder.durable("durable-quorum-queue").quorum().build();
    }

    @Bean(name = "durable-classic-queue")
    Queue durableClassicQueue() {
        return QueueBuilder.durable("durable-classic-queue").build();
    }

    @Bean(name = "non-durable-queue")
    Queue nonDurableQueue() {
        return QueueBuilder.nonDurable("non-durable-queue").build();
    }
}
