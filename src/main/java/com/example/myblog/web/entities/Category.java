package com.example.myblog.web.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Lob
    @Column(length = 512)
    private String description;
    @Column
    private int postsNum;
    @ManyToMany
    private List<Category> categoryPosts;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPostsNum() {
        return postsNum;
    }

    public void setPostsNum(int postsNum) {
        this.postsNum = postsNum;
    }

    public List<Category> getCategoryPosts() {
        return categoryPosts;
    }

    public void setCategoryPosts(List<Category> categoryPosts) {
        this.categoryPosts = categoryPosts;
    }
}
