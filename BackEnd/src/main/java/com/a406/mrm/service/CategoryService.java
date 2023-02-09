package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.model.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryInsertDto join(CategoryInsertDto categoryInsertDto) throws Exception;
    void delete(int id) throws Exception;
    String update(int id, String name) throws Exception;
    List<CategoryResponseDto> listCategory(int category_id) throws Exception;

}
