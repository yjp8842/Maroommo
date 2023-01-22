package com.a406.mrm.repository;

import com.a406.mrm.model.entity.UserHasRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHasRoomRepository extends JpaRepository<UserHasRoom, Integer> {
}