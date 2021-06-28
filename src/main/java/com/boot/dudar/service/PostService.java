package com.boot.dudar.service;

import com.boot.dudar.models.Post;

import java.util.List;

public interface PostService {
    Post create(Post post);
    List<Post> getAll();

    Post update(Post oldPost, Post newPost);

    void delete(Post post);

}
