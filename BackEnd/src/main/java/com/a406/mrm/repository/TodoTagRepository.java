package com.a406.mrm.repository;

import com.a406.mrm.model.entity.TodoTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoTagRepository extends JpaRepository<TodoTag,Integer> {
}
