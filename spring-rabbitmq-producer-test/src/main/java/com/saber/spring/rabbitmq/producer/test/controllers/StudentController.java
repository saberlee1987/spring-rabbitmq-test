package com.saber.spring.rabbitmq.producer.test.controllers;

import com.saber.spring.rabbitmq.producer.test.dto.ResponseDtoStudent;
import com.saber.spring.rabbitmq.producer.test.entities.StudentEntity;
import com.saber.spring.rabbitmq.producer.test.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentEntity> addStudent(@RequestBody com.saber.spring.rabbitmq.producer.test.dto.StudentDto studentDto) {
        log.info("Request for add Student with body ===> {}", studentDto);
        StudentEntity studentDtoSaved = this.studentService.addStudent(studentDto);
        log.info("Response for addStudent ===> {}", studentDtoSaved);
        return ResponseEntity.ok(studentDtoSaved);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseDtoStudent> findAllStudents() {
        log.info("findAll Students called ...........");
        ResponseDtoStudent responseDtoStudent = this.studentService.findAll();
        log.info("ResponseDtoStudent  ====> {}", responseDtoStudent);
        return ResponseEntity.ok(responseDtoStudent);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentEntity> findById(@PathVariable(name = "id") Integer id) {
       StudentEntity studentEntity = this.studentService.findById(id);
        if (studentEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentEntity);
    }
}
