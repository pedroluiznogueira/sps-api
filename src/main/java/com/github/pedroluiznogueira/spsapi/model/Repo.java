package com.github.pedroluiznogueira.spsapi.model;

import lombok.Data;

@Data
public class Repo {

    private String repoName;
    private String repoOwner;
    private String repoUrl;
    private String userName;
}
