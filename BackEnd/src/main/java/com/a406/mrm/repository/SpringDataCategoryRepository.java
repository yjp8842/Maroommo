package com.a406.mrm.repository;

import com.a406.mrm.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataCategoryRepository extends JpaRepository<Category, Integer>, CategoryRepository {

    List findByroom_Id(@Param(value = "roomID") int roomID);
}