package com.saber.spring.rabbitmq.producer.test.services;

import com.saber.spring.rabbitmq.producer.test.dto.ResponseDtoStudent;
import com.saber.spring.rabbitmq.producer.test.dto.StudentDto;
import com.saber.spring.rabbitmq.producer.test.entities.Student;

public interface StudentService {
    Student addStudent(StudentDto studentDto);
    ResponseDtoStudent findAll();
    Student findById(Integer id);
}
