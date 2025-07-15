package com.pessoal.forum.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private long id;
    private String title;
    private String content;
    private String author;
    private int likes;
}