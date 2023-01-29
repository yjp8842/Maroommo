package com.a406.mrm.repository;

import com.a406.mrm.model.entity.TodoTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTagRepository extends JpaRepository<TodoTag,Integer> {
    void deleteAllByTodoId(int TodoId);
}
