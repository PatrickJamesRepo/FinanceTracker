package org.example.financeappbackend.controller;

import org.example.financeappbackend.entity.Category;
import org.example.financeappbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryControllor {

    @Autowired
    private CategoryService categoryService;

    // Get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Get categories by type
    @GetMapping("/type/{type}")
    public List<Category> getCategoriesByType(@PathVariable Category.CategoryType type) {
        return categoryService.getCategoriesByType(type);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Optional<Category> updatedCategory = categoryService.updateCategory(id, categoryDetails);
        return updatedCategory.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
