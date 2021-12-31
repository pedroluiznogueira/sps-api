package com.github.pedroluiznogueira.spsapi.controller;

import com.github.pedroluiznogueira.spsapi.model.Student;
import com.github.pedroluiznogueira.spsapi.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping("/insert")
    public String inserted() {
        Student student = new Student("pedro", 21);
        studentRepository.insert(student);
        return "inserted";
    }

    @GetMapping("find/all")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
