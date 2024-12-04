package com.example.day34lab11blog.Service;


import com.example.day34lab11blog.ApiResponse.ApiException;
import com.example.day34lab11blog.Model.Category;
import com.example.day34lab11blog.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id,Category category) {
        Category oldCategory = categoryRepository.findCategoriesByCategoryId(id);
        if (oldCategory == null) {
            throw new ApiException("no category with this id was found");
        }
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findCategoriesByCategoryId(id);
        if (category == null) {
            throw new ApiException("no category with this id was found");
        }
        categoryRepository.delete(category);
    }
}
