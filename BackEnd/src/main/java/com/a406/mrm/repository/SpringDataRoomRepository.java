package com.a406.mrm.repository;


import com.a406.mrm.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRoomRepository extends JpaRepository<Room, Integer>, RoomRepository {

}