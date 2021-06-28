package com.boot.dudar.repositories;

import com.boot.dudar.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
