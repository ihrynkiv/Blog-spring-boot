package com.boot.dudar.repositories;

import com.boot.dudar.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
