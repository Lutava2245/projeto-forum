package com.pessoal.forum.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoal.forum.domain.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String title);

    List<Post> findByAuthorContaining(String author);

    Post findByTitleAndAuthor(String title, String author);
}