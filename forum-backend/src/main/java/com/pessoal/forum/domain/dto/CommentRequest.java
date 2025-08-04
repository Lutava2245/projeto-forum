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
public class CommentRequest {
    @Size(min = 5, max = 500, message = "Content must be between 10 and 500 characters")
    private String content;
    @Size(max = 50, message = "Author name must be between 3 and 50 characters")
    private String author;
}
