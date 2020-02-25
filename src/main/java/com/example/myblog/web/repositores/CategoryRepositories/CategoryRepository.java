package com.example.myblog.web.repositores.CategoryRepositories;

import com.example.myblog.web.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
