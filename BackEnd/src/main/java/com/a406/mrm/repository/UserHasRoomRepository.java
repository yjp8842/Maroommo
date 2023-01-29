package com.a406.mrm.repository;

import com.a406.mrm.model.entity.UserHasRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHasRoomRepository extends JpaRepository<UserHasRoom, Integer> {
    List<UserHasRoom> findByUserId(@Param("userId") String userId);
}