package com.saber.spring.rabbitmq.producer.test.producer;

import com.saber.spring.rabbitmq.producer.test.entities.Student;
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

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Student student){
        Object studentResponse =  this.rabbitTemplate.convertSendAndReceive(this.exchangeName,this.exchangeKey,student);
        log.info("Received response student from consumer ====> {}",studentResponse);
    }
}
