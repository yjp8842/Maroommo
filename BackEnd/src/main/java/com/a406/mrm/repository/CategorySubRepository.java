package com.a406.mrm.repository;


import com.a406.mrm.model.entity.CategorySub;

import java.util.List;

public interface CategorySubRepository {

    CategorySub save(CategorySub categorySub);

    void deleteById (int id);

    CategorySub findById(int id);

    List<CategorySub> findAll();

    List findBycategory_Id (int category_id);
}
