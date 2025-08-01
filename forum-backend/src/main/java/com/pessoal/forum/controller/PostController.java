package com.pessoal.forum.controller;

import com.pessoal.forum.domain.dto.PostDTO;
import com.pessoal.forum.domain.dto.PostRequest;
import com.pessoal.forum.domain.service.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable long id) {
        PostDTO post = postService.getPostById(id);

        if (post != null) {
            return ResponseEntity.ok(post);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();

        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        }
        
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostRequest postRequest) {
        PostDTO post = postService.createPost(postRequest);
        
        if (post != null) {
            return ResponseEntity.ok(post);
        }

        return ResponseEntity.internalServerError().build();
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable long id, @RequestBody PostRequest postRequest) {
        PostDTO post = postService.updatePost(id, postRequest);

        if (post != null) {
            return ResponseEntity.ok(post);
        }

        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable long id) { 
        boolean deleted = postService.deletePost(id);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
    }
}