package com.a406.mrm.repository;

import com.a406.mrm.model.entity.TodoTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTimeRepository extends JpaRepository<TodoTime, Integer> {
    TodoTime findByTodoId(@Param("todoId") int todoId);
//
//    @Modifying
//    @Query(value = "UPDATE todo_time " +
//            "SET end_time = now() , total_time = now()-start_time" +
//            "WHERE id = :todoTimeId ",nativeQuery = true)
//    void updateEndTimeAndTotalTime(@Param("todo_time_id") int todoTimeId);
}