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

    public CategorySubInsertDto join(CategorySubInsertDto insertDto){
        CategorySub categorySub = new CategorySub(insertDto, categoryRepository.findById(insertDto.getCategory_id()));
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

}
