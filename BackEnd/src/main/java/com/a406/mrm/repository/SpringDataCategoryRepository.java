package com.a406.mrm.repository;

import com.a406.mrm.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoryRepository extends JpaRepository<Category, Integer>, CategoryRepository {

}