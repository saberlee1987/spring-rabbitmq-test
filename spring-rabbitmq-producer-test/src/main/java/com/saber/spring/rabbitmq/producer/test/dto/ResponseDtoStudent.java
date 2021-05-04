package com.saber.spring.rabbitmq.producer.test.dto;

import com.saber.entities.StudentDto;
import com.saber.spring.rabbitmq.producer.test.entities.StudentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
public class ResponseDtoStudent {
    private List<StudentEntity> response;
}
