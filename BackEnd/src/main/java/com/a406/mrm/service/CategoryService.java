package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategoryInsertDto;
import com.a406.mrm.model.dto.CategoryResponseDto;
import com.a406.mrm.model.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryInsertDto join(CategoryInsertDto categoryInsertDto, int roomId);

    void delete(int id);

    String update(int id, String name);

//    List listCategory(int room_id);

    List<CategoryResponseDto> listCategory(int room_id);

}
