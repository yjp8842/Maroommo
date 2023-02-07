package com.a406.mrm.repository;

import com.a406.mrm.model.entity.TodoTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoTimeRepository extends JpaRepository<TodoTime, Integer> {
    TodoTime findByTodoId(@Param("todoId") int todoId);

    List<TodoTime> findById(@Param("room_id") int room_id);

    @Modifying
    @Query(value = "UPDATE todo_time " +
            "SET end_time = now() , total_time = TIMEDIFF(now(),start_time)" +
            "WHERE id = :todoTimeId ", nativeQuery = true)
    void updateEndTimeAndTotalTime(@Param("todoTimeId") int todoTimeId);


}