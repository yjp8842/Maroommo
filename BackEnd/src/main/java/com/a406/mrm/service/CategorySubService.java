package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategorySubInsertDto;
import com.a406.mrm.model.entity.CategorySub;

import java.util.List;

public interface CategorySubService {

    CategorySubInsertDto join(CategorySubInsertDto categorySubInsertDto, int category_id);

    void delete(int id);

    CategorySub update(int id, String name);

    List listCategorySub(int category_id);

    CategorySub findOne(int id);

}
