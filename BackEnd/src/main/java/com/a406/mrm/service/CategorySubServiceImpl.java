package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategorySubInsertDto;
import com.a406.mrm.model.entity.CategorySub;
import com.a406.mrm.repository.CategoryRepository;
import com.a406.mrm.repository.CategorySubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategorySubServiceImpl implements CategorySubService{

    private final CategorySubRepository categorySubRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategorySubServiceImpl(CategorySubRepository categorySubRepository, CategoryRepository categoryRepository) {
        this.categorySubRepository = categorySubRepository;
        this.categoryRepository = categoryRepository;
    }

    public CategorySubInsertDto join(CategorySubInsertDto categorySubInsertDto, int category_id){
        CategorySub categorySub = new CategorySub(categorySubInsertDto, categoryRepository.findById(category_id));
        return new CategorySubInsertDto(categorySubRepository.save(categorySub));
    }

    public void delete(int id){
        categorySubRepository.deleteById(id);
    }

    public String update_name(int id, String name){
        CategorySub categorySub = categorySubRepository.findById(id);
        categorySub.setName(name);
        return categorySubRepository.save(categorySub).getName();
    }

    public int update_subtype(int id, int subtype){
        CategorySub categorySub = categorySubRepository.findById(id);
        categorySub.setSubtype(subtype);
        return categorySubRepository.save(categorySub).getSubtype();
    }


    public List listCategorySub(int category_id) { return categorySubRepository.findBycategory_Id(category_id);}

    public CategorySub findOne(int id) {
        return categorySubRepository.findById(id);
    }
}
