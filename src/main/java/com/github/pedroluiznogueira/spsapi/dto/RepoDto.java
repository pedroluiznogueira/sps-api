package com.github.pedroluiznogueira.spsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RepoDto {

    private Long id;
    private String name;
    private String owner;
    private String url;
}
