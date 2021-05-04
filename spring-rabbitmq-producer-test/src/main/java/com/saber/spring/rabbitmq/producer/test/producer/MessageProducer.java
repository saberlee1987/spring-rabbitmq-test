package com.saber.spring.rabbitmq.producer.test.producer;

import com.saber.entities.StudentDto;
import com.saber.spring.rabbitmq.producer.test.entities.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageProducer {

    @Value(value = "${spring.rabbitmq.exchange.name}")
    private String exchangeName;
    @Value(value = "${spring.rabbitmq.exchange.key}")
    private String exchangeKey;

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(StudentEntity studentEntity){
        StudentDto studentDto = createStudentDto(studentEntity);
        Object studentResponse =  this.rabbitTemplate.convertSendAndReceive(this.exchangeName,this.exchangeKey, studentDto);
        if (studentResponse instanceof StudentDto){
          studentDto = (StudentDto) studentResponse;
          log.info("studentDto from consumer changed is {}",studentDto);
        }

        log.info("Received response student from consumer ====> {}",studentResponse);
    }

    private StudentDto createStudentDto(StudentEntity studentEntity){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentEntity.getId());
        studentDto.setAge(studentEntity.getAge());
        studentDto.setField(studentEntity.getField());
        studentDto.setLastName(studentEntity.getLastName());
        studentDto.setFirstName(studentEntity.getFirstName());
        return studentDto;
    }
}
