package com.a406.mrm.repository;


import com.a406.mrm.model.entity.CategorySub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorySubRepository extends JpaRepository<CategorySub, Integer> {

    CategorySub save(CategorySub categorySub);

    void deleteById (int id);

    CategorySub findById(int id);

    List findBycategory_Id (int category_id);
}
