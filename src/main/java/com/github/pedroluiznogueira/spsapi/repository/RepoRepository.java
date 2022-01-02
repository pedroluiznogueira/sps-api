package com.github.pedroluiznogueira.spsapi.repository;

import com.github.pedroluiznogueira.spsapi.model.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoRepository extends JpaRepository<Repo, Long> {
    List<Repo> findAllByUserId(Integer id);
}
