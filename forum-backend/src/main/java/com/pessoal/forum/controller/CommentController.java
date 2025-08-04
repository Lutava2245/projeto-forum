package com.pessoal.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoal.forum.domain.dto.CommentDTO;
import com.pessoal.forum.domain.dto.CommentRequest;
import com.pessoal.forum.domain.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable long id) {
        CommentDTO comment = commentService.getCommentById(id);

        if (comment != null) {
            return ResponseEntity.ok(comment);
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/posts/{post_id}/comments")
    public ResponseEntity<Iterable<CommentDTO>> getAllPostComments(@PathVariable long post_id) {
        List<CommentDTO> comments = commentService.getAllCommentsByPost(post_id);
    
        if (!comments.isEmpty()) {
            return ResponseEntity.ok(comments);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/comments")
    public ResponseEntity<Iterable<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments();

        if (!comments.isEmpty()) {
            return ResponseEntity.ok(comments);
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/posts/{post_id}/comments/create")
    public ResponseEntity<CommentDTO> createComment(@PathVariable long post_id, @RequestBody CommentRequest commentRequest) {
        CommentDTO comment = commentService.createComment(post_id, commentRequest);

        if (comment != null) {
            return ResponseEntity.ok(comment);
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/comments/update/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable long id, @RequestBody CommentRequest commentRequest) {
        CommentDTO comment = commentService.updateComment(id, commentRequest);

        if (comment != null) {
            return ResponseEntity.ok(comment);
        }
        
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/comments/delete/{id}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable long id) {
        boolean deleted = commentService.deleteComment(id);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
    }
}