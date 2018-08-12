package com.example.testtask.repository;

import com.example.testtask.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByCategory(String category);
}
