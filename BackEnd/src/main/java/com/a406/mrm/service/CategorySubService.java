package com.a406.mrm.service;

import com.a406.mrm.model.dto.CategorySubInsertDto;
import com.a406.mrm.model.entity.CategorySub;

import java.util.List;

public interface CategorySubService {

    CategorySubInsertDto join(CategorySubInsertDto categorySubInsertDto) throws Exception;
    void delete(int id) throws Exception;
    String update_name(int id, String name) throws Exception;
}
