package com.pessoal.forum.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pessoal.forum.domain.dto.PostDTO;
import com.pessoal.forum.domain.dto.PostRequest;
import com.pessoal.forum.domain.model.Post;

@Service
public interface PostService {
    public PostDTO createPost(PostRequest postRequest);

    public PostDTO getPostById(long id);

    public List<PostDTO> getAllPosts();
    
    public List<PostDTO> searchPostsByTitle(String title);
    
    public List<PostDTO> searchPostsByAuthor(String author);

    public PostDTO updatePost(long id, PostRequest postRequest);
    
    public boolean deletePost(long id);

    public PostDTO convertToPostDTO(Post post);

    public boolean verifyExistingPost(PostRequest postRequest);
}