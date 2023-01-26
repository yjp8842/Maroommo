package com.a406.mrm.repository;

import com.a406.mrm.model.entity.CategorySub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataCategorySubRepository extends JpaRepository<CategorySub, Integer>, CategorySubRepository {

    List findBycategory_Id(@Param(value = "categoryID") int categoryID);
}
