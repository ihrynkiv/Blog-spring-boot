package com.boot.dudar.service;

import com.boot.dudar.models.Post;
import com.boot.dudar.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImp implements PostService{

    private PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post update(Post oldPost, Post newPost) {
        newPost.setId(oldPost.getId());
        return postRepository.save(newPost);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
