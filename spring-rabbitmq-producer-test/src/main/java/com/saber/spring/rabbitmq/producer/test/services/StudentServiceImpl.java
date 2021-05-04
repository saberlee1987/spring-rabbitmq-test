package com.saber.spring.rabbitmq.producer.test.services;

import com.saber.entities.StudentDto;
import com.saber.spring.rabbitmq.producer.test.dto.ResponseDtoStudent;
import com.saber.spring.rabbitmq.producer.test.entities.StudentEntity;
import com.saber.spring.rabbitmq.producer.test.producer.MessageProducer;
import com.saber.spring.rabbitmq.producer.test.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final MessageProducer messageProducer;

    public StudentServiceImpl(StudentRepository studentRepository, MessageProducer messageProducer) {
        this.studentRepository = studentRepository;
        this.messageProducer = messageProducer;
    }

    @Override
    public StudentEntity addStudent(com.saber.spring.rabbitmq.producer.test.dto.StudentDto studentDto) {
        StudentEntity student = createStudent(studentDto);
        log.info("StudentDto saved ====> {}", studentDto);
        StudentEntity studentDtoSaved = this.studentRepository.save(student);
        log.info("Student send with messaging ===> {}", studentDtoSaved);
        this.messageProducer.sendMessage(studentDtoSaved);
        return studentDtoSaved;
    }

    @Override
    public ResponseDtoStudent findAll() {
        List<StudentEntity> studentDtoList = this.studentRepository.findAll();
        ResponseDtoStudent responseDtoStudent = new ResponseDtoStudent();
        responseDtoStudent.setResponse(studentDtoList);
        return responseDtoStudent;
    }

    @Override
    public StudentEntity findById(Integer id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    private StudentEntity createStudent(com.saber.spring.rabbitmq.producer.test.dto.StudentDto studentDto) {
        StudentEntity student = new StudentEntity();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        student.setField(studentDto.getField());
        return student;
    }
}
