package com.a406.mrm.repository;

import com.a406.mrm.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoomRepository {
    Room findById(int id);
}