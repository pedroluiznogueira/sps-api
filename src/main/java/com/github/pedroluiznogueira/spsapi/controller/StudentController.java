package com.github.pedroluiznogueira.spsapi.controller;

import com.github.pedroluiznogueira.spsapi.model.Repo;
import com.github.pedroluiznogueira.spsapi.model.Student;
import com.github.pedroluiznogueira.spsapi.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @PostMapping("/insert")
    public Student inserted(@RequestBody Student student) {
        studentRepository.insert(student);
        return student;
    }

    @GetMapping("find/all")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("find/all/repos/{name}")
    public List<Repo> findStudentRepos(@PathVariable ("name") String name) {
        Student student = studentRepository.findByName(name);
        return student.getRepos();
    }

    @PostMapping("insert/repo")
    public String insertStudentRepo(@RequestBody Repo repo) {
        String studentName = repo.getStudentName();
        Student student = studentRepository.findByName(studentName);
        List<Repo> studentRepos = student.getRepos();
        studentRepos.add(repo);
        student.setRepos(studentRepos);
        studentRepository.save(student);
        return "repo inserted";
    }
}
