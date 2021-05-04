package com.saber.spring.rabbitmq.producer.test.services;

import com.saber.spring.rabbitmq.producer.test.dto.ResponseDtoStudent;
import com.saber.spring.rabbitmq.producer.test.dto.StudentDto;
import com.saber.spring.rabbitmq.producer.test.entities.StudentEntity;

public interface StudentService {
    StudentEntity addStudent(StudentDto studentDto);
    ResponseDtoStudent findAll();
    StudentEntity findById(Integer id);
}
