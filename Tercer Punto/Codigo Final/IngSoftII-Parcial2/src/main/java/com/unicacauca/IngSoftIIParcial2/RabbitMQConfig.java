package com.unicacauca.IngSoftIIParcial2;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${sacavix.queue.name}")
    String message;
    @Bean
    public Queue queue() {
        return new Queue(message, true);
    }
}
