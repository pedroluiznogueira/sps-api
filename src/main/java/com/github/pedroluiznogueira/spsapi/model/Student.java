package com.github.pedroluiznogueira.spsapi.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Student {

    @Id
    private String id;
    private String name;
    private Integer age;
    private List<Repo> repos;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
