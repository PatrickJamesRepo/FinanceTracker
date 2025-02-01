package org.example.financeappbackend.service;

import org.example.financeappbackend.entity.Category;
import org.example.financeappbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoriesByType(Category.CategoryType type) {
        return categoryRepository.findByType(type);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> updateCategory(Long id, Category categoryDetails) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(categoryDetails.getName());
            category.setType(categoryDetails.getType());
            return categoryRepository.save(category);
        });
    }

    public boolean deleteCategory(Long id) {
        return categoryRepository.findById(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
    }
}
