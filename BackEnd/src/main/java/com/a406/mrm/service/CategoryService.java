package com.a406.mrm.service;

import com.a406.mrm.model.entity.Category;
import com.a406.mrm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category join(Category category){
        return categoryRepository.save(category);
    }

    public void delete(int id){
        categoryRepository.deleteById(id);
    }

    public void update(int id, String name){
        Category category = categoryRepository.findById(id);
        category.setName(name);
    }

    public List<Category> findCategory(){ return categoryRepository.findAll();}

    public Category findOne(int id) {
        return categoryRepository.findById(id);
    }
}
