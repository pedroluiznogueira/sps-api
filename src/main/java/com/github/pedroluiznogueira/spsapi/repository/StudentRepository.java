package com.github.pedroluiznogueira.spsapi.repository;


import com.github.pedroluiznogueira.spsapi.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

}
