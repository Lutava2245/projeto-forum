package com.pessoal.forum.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoal.forum.domain.dto.CommentDTO;
import com.pessoal.forum.domain.dto.CommentRequest;
import com.pessoal.forum.domain.model.Comment;
import com.pessoal.forum.domain.model.Post;
import com.pessoal.forum.domain.repository.CommentRepository;
import com.pessoal.forum.domain.repository.PostRepository;
import com.pessoal.forum.domain.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDTO createComment(long post_id, CommentRequest commentRequest) {
        Post post = postRepository.findById(post_id).orElse(null);

        if (post == null) {
            return null;
        }

        Comment comment = new Comment(
            commentRequest.getContent(),
            commentRequest.getAuthor());
        
        comment.setPost(post);
        
        commentRepository.save(comment);

        return convertToCommentDTO(comment);
    }

    @Override
    public CommentDTO getCommentById(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null) {
            return null;
        }

        return convertToCommentDTO(comment);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
        .map(this::convertToCommentDTO)
        .collect(Collectors.toList());
    }


    @Override
    public List<CommentDTO> getAllCommentsByPost(long post_id) {
        Post post = postRepository.findById(post_id).orElse(null);

        if (post == null) {
            return List.of();
        }

        return commentRepository.findByPost(post).stream()
        .map(this::convertToCommentDTO)
        .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> searchCommentsByAuthor(String author) {
        return commentRepository.findByAuthorContaining(author).stream()
            .map(this::convertToCommentDTO)
            .collect(Collectors.toList());
    }

    @Override
    public CommentDTO updateComment(long id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null) {
            return null;
        }

        comment.setAuthor(commentRequest.getAuthor());
        comment.setContent(commentRequest.getContent());

        commentRepository.save(comment);

        return convertToCommentDTO(comment);
    }

    @Override
    public boolean deleteComment(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
    
        if (comment != null) {
            commentRepository.delete(comment);
            return true;
        }

        return false;
    }

    @Override
    public CommentDTO convertToCommentDTO(Comment comment) {
        return new CommentDTO(
            comment.getId(),
            comment.getContent(),
            comment.getAuthor(),
            comment.getCreatedAt(),
            comment.getUpdatedAt());
    }
}