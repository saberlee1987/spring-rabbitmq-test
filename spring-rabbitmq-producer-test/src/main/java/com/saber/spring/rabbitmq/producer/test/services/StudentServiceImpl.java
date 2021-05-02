package com.saber.spring.rabbitmq.producer.test.services;

import com.saber.spring.rabbitmq.producer.test.dto.ResponseDtoStudent;
import com.saber.spring.rabbitmq.producer.test.dto.StudentDto;
import com.saber.spring.rabbitmq.producer.test.entities.Student;
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
    public Student addStudent(StudentDto studentDto) {
        Student student = createStudent(studentDto);
        log.info("StudentDto saved ====> {}", studentDto);
        Student studentSaved = this.studentRepository.save(student);
        log.info("Student send with messaging ===> {}", studentSaved);
        this.messageProducer.sendMessage(studentSaved);
        return studentSaved;
    }

    @Override
    public ResponseDtoStudent findAll() {
        List<Student> studentList = this.studentRepository.findAll();
        ResponseDtoStudent responseDtoStudent = new ResponseDtoStudent();
        responseDtoStudent.setResponse(studentList);
        return responseDtoStudent;
    }

    @Override
    public Student findById(Integer id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    private Student createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        student.setField(studentDto.getField());
        return student;
    }
}
