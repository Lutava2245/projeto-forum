package com.pessoal.forum.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostRequest {
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;
    @Size(min = 10, max = 500, message = "Content must be between 10 and 500 characters")
    private String content;
    @Size(min = 3, max = 50, message = "Author name must be between 3 and 50 characters")
    private String author;
}