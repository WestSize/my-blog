package com.example.myblog.web.repositores.PostRepositories;

import com.example.myblog.web.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
}
