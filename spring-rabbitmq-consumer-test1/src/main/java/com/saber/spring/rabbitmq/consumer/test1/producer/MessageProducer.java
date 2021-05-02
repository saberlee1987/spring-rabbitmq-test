package com.saber.spring.rabbitmq.consumer.test1.producer;

import com.saber.spring.rabbitmq.consumer.test1.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageProducer {

    @Value(value = "${spring.rabbitmq.exchange.sender.name}")
    private String exchangeName;
    @Value(value = "${spring.rabbitmq.exchange.sender.key}")
    private String exchangeKey;

    private final  RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Student student){
        log.info("Send Student for producer ===> {}",student);
        this.rabbitTemplate.convertAndSend(this.exchangeName,exchangeKey,student);
    }
}
