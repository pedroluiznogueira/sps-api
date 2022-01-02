package com.github.pedroluiznogueira.spsapi.model;

import lombok.Data;

@Data
public class Repo {

    private String name;
    private String owner;
    private String url;
    private String userName;
}
