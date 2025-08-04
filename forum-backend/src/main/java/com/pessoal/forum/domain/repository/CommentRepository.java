package com.pessoal.forum.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoal.forum.domain.model.Comment;
import com.pessoal.forum.domain.model.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthorContaining(String author);

    List<Comment> findByPost(Post post);
}
