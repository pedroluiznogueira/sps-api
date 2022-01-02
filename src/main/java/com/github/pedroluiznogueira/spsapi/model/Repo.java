package com.github.pedroluiznogueira.spsapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Repo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String owner;
    private String url;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
