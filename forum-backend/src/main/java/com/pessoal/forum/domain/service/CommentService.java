package com.pessoal.forum.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pessoal.forum.domain.dto.CommentDTO;
import com.pessoal.forum.domain.dto.CommentRequest;
import com.pessoal.forum.domain.model.Comment;

@Service
public interface CommentService {
    public CommentDTO createComment(long post_id, CommentRequest commentRequest);

    public CommentDTO getCommentById(long id);

    public List<CommentDTO> getAllComments();

    public List<CommentDTO> getAllCommentsByPost(long post_id);

    public List<CommentDTO> searchCommentsByAuthor(String author);

    public CommentDTO updateComment(long id, CommentRequest commentRequest);

    public boolean deleteComment(long id);

    public CommentDTO convertToCommentDTO(Comment comment);
}
