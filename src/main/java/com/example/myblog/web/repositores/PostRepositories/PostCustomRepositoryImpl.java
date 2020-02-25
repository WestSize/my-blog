package com.example.myblog.web.repositores.PostRepositories;

import com.example.myblog.web.entities.Category;
import com.example.myblog.web.entities.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class PostCustomRepositoryImpl implements PostCustomRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Post> showPostsByCategoryId(long id) {
        Query query = entityManager.createNativeQuery("select em.* from posts as em where em.category_id LIKE ?", Post.class);
        query.setParameter(1, id);
        return query.getResultList();
    }
}
