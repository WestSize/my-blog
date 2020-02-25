package com.example.myblog.web.repositores.PostRepositories;

import com.example.myblog.web.entities.Post;

import java.util.List;

public interface PostCustomRepository {
    List<Post> showPostsByCategoryId(long id);
}
