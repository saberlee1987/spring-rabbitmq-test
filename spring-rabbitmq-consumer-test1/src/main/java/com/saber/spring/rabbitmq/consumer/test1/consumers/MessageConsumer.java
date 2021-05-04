package com.saber.spring.rabbitmq.consumer.test1.consumers;

import com.saber.entities.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.exchange.queue}")
    public StudentDto consumeMessage(StudentDto student) {
        log.info("Received Student Message ===> {}", student);
        student.setAge(student.getAge() + 10);
        return student;
    }
}
