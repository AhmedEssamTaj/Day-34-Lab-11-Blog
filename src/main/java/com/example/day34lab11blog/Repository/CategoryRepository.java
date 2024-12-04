package com.example.day34lab11blog.Repository;

import com.example.day34lab11blog.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoriesByCategoryId(Integer categoryId);

}
