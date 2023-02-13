package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.model.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto join(CategoryInsertDto categoryInsertDto) throws Exception;
    void delete(int id) throws Exception;
    String update(int id, String name) throws Exception;
    List<CategoryResponseDto> CategoryDetail(int category_id) throws Exception;

}