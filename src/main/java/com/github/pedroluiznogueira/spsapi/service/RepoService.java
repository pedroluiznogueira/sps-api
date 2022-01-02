package com.github.pedroluiznogueira.spsapi.service;

import com.github.pedroluiznogueira.spsapi.model.Repo;
import com.github.pedroluiznogueira.spsapi.model.User;
import com.github.pedroluiznogueira.spsapi.repository.RepoRepository;
import com.github.pedroluiznogueira.spsapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepoService {

    private final RepoRepository repoRepository;
    private final UserRepository userRepository;

    public List<Repo> findAllByUserId(Integer id) {
        List<Repo> repos = repoRepository.findAllByUserId(id);
        if (repos.isEmpty()) return null;

        return repos;
    }

    public Repo insertRepo(Integer id, Repo repo) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) return null;
        repo.setUser(user.get());

        return repoRepository.save(repo);
    }

    public Repo deleteRepo(Long id) {
        Repo repo = repoRepository.findById(id).get();
        if (repo == null) return null;
        repoRepository.delete(repo);

        return repo;
    }

    public Repo findRepoByName(String name, Integer id) {
        List<Repo> listToSearchOn = repoRepository.findAllByUserId(id);

        for (Integer i = 0; i < listToSearchOn.size(); i++) {
            if (listToSearchOn.get(i).getName().equals(name)) {
                return listToSearchOn.get(i);
            }
        }
        return null;
    }
}
