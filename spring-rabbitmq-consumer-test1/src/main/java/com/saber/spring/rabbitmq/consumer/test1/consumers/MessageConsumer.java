package com.saber.spring.rabbitmq.consumer.test1.consumers;

import com.saber.spring.rabbitmq.consumer.test1.entities.Student;
import com.saber.spring.rabbitmq.consumer.test1.producer.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {
    private final MessageProducer messageProducer;

    public MessageConsumer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @RabbitListener(queues = "${spring.rabbitmq.exchange.receiver.queue}")
    public void consumeMessage(Student student) {
        log.info("Received Student Message ===> {}", student);
        student.setAge(student.getAge() + 10);
        this.messageProducer.sendMessage(student);
    }
}
