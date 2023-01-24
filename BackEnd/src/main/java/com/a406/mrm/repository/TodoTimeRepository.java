package com.a406.mrm.repository;

import com.a406.mrm.model.entity.TodoTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoTimeRepository extends JpaRepository<TodoTime, Integer> {
}