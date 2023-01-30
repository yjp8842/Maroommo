package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategorySubInsertDto;
import com.a406.mrm.model.entity.CategorySub;

import java.util.List;

public interface CategorySubService {

    CategorySubInsertDto join(CategorySubInsertDto categorySubInsertDto, int category_id);

    void delete(int id);

    String update_name(int id, String name);

    int update_subtype(int id, int subtype);

    List listCategorySub(int category_id);

}
