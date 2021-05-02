package com.saber.spring.rabbitmq.producer.test.dto;

import com.saber.spring.rabbitmq.producer.test.entities.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
public class ResponseDtoStudent {
    private List<Student> response;
}
