package com.a406.mrm.repository;

import com.a406.mrm.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByUserId(@Param("userId") String userId);
    List<Schedule> findByRoomId(int roomId);
}