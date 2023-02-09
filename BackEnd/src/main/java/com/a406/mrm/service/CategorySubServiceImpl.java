package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategorySubInsertDto;
import com.a406.mrm.model.entity.Category;
import com.a406.mrm.model.entity.CategorySub;
import com.a406.mrm.repository.CategoryRepository;
import com.a406.mrm.repository.CategorySubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategorySubServiceImpl implements CategorySubService{

    private final CategorySubRepository categorySubRepository;
    private final CategoryRepository categoryRepository;

    public CategorySubInsertDto join(CategorySubInsertDto insertDto) throws Exception{
        Category category = categoryRepository.findById(insertDto.getCategory_id());
        CategorySubInsertDto categorySubInsertDto = null;

        if(category != null){
            CategorySub categorySub = new CategorySub(insertDto, category);
            categorySub = categorySubRepository.save(categorySub);
            categorySubInsertDto = new CategorySubInsertDto(categorySub);
        }

        return categorySubInsertDto;
    }

    public void delete(int id) throws Exception{
        categorySubRepository.deleteById(id);
    }

    public String update_name(int id, String name) throws Exception{
        CategorySub categorySub = categorySubRepository.findById(id);
        String categorySubName = null;

        if(categorySub != null){
            categorySub.setName(name);
            categorySub = categorySubRepository.save(categorySub);
            categorySubName = categorySub.getName();
        }

        return categorySubName;
    }

}
