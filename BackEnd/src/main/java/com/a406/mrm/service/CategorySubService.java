package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategorySubInsertDto;
import com.a406.mrm.model.entity.CategorySub;

import java.util.List;

public interface CategorySubService {

    CategorySubInsertDto join(CategorySubInsertDto categorySubInsertDto);

    void delete(int id);

    String update_name(int id, String name);



}
