package com.saber.spring.rabbitmq.producer.test.repositories;


import com.saber.spring.rabbitmq.producer.test.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {

}
