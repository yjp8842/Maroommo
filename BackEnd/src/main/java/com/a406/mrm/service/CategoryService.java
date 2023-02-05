package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.model.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryInsertDto join(CategoryInsertDto categoryInsertDto, int roomId);

    void delete(int id);

    String update(int id, String name);

    List<CategoryResponseDto> listCategory(int category_id);

}
