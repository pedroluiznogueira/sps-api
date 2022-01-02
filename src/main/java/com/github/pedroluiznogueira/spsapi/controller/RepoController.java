package com.github.pedroluiznogueira.spsapi.controller;

import com.github.pedroluiznogueira.spsapi.dto.RepoDto;
import com.github.pedroluiznogueira.spsapi.model.Repo;
import com.github.pedroluiznogueira.spsapi.service.RepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repos")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RepoController {

    private final RepoService repoService;

    @GetMapping("/find/all/{id}")
    public ResponseEntity<List<Repo>> findAllByUserId(@PathVariable("id") Integer id) {
        List<Repo> repos = repoService.findAllByUserId(id);
        if (repos == null) return new ResponseEntity("there's no repository for this user yet", HttpStatus.BAD_REQUEST);

        return new ResponseEntity(repos, HttpStatus.OK);
    }

    @PostMapping("/insert/repo/user/{id}")
    public ResponseEntity<Repo> insertRepo(@PathVariable ("id") Integer id,@RequestBody Repo repo) {
        Repo respRepo = repoService.insertRepo(id, repo);
        if (respRepo == null) return new ResponseEntity("there's no user with the given id", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(respRepo, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/repo/{id}")
    public ResponseEntity<Repo> deleteRepo(@PathVariable ("id") Long id) {
        Repo repo = repoService.deleteRepo(id);
        if (repo == null) return new ResponseEntity("there's no repo with the given id", HttpStatus.NOT_FOUND);

        return new ResponseEntity(repo, HttpStatus.OK);
    }

    @GetMapping("/find/repo/by/name/{name}/by/user/id/{id}")
    public ResponseEntity<RepoDto> findRepoByName(@PathVariable ("name") String name, @PathVariable ("id") Integer id) throws InterruptedException {
        Thread.sleep(3000);
        Repo repo = repoService.findRepoByName(name, id);
        if (repo == null) return new ResponseEntity("repository not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity(new RepoDto(repo.getId(), repo.getName(), repo.getOwner(), repo.getUrl()), HttpStatus.OK);
    }
}
