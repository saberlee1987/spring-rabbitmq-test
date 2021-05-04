package com.saber.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString

public class StudentDto implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String field;
}
