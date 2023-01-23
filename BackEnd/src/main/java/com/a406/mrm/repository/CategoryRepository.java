package com.a406.mrm.repository;

import com.a406.mrm.model.entity.Category;

import java.util.List;

public interface CategoryRepository {

    Category save(Category category);

    void deleteById (int id);

    Category findById(int id);

    List<Category> findAll();
}
