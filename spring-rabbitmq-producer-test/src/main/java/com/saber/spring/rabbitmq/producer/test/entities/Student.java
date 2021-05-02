package com.saber.spring.rabbitmq.producer.test.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "firstName",length = 60)
    private String firstName;
    @Column(name = "lastName",length = 70)
    private String lastName;
    private Integer age;
    @Column(name = "field",length = 35)
    private String field;
}
