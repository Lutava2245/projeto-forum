package com.pessoal.forum.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoal.forum.domain.dto.PostDTO;
import com.pessoal.forum.domain.dto.PostRequest;
import com.pessoal.forum.domain.model.Post;
import com.pessoal.forum.domain.repository.PostRepository;
import com.pessoal.forum.domain.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO createPost(PostRequest postRequest) {
        if (verifyExistingPost(postRequest)) {
            Post post = postRepository.save(
                new Post(postRequest.getTitle(),
                postRequest.getContent(),
                postRequest.getAuthor()));

            return convertToPostDTO(post);
        }
        return null;
    }

    @Override
    public PostDTO getPostById(long id) {
        return postRepository.findById(id)
            .map(this::convertToPostDTO)
            .orElse(null);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
            .map(this::convertToPostDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title).stream()
            .map(this::convertToPostDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPostsByAuthor(String author) {
        return postRepository.findByAuthorContaining(author).stream()
            .map(this::convertToPostDTO)
            .collect(Collectors.toList());
    }

    @Override
    public PostDTO updatePost(long id, PostRequest postRequest) {
        Post post = postRepository.findById(id).orElse(null);

        if (post == null) {
            return null;
        }

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(postRequest.getAuthor());

        return convertToPostDTO(post);
    }

    @Override
    public boolean deletePost(long id) {
        Post post = postRepository.findById(id).orElse(null);

        if (post != null) {
            postRepository.delete(post);
            return true;
        }

        return false;
    }

    @Override
    public PostDTO convertToPostDTO(Post post) {
        return new PostDTO(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getAuthor(),
            post.getLikes());
    }

    public boolean verifyExistingPost(PostRequest postRequest) {
        Post existingPost = postRepository.findByTitleAndAuthor(postRequest.getTitle(), postRequest.getAuthor());
        return existingPost == null;
    }
}
