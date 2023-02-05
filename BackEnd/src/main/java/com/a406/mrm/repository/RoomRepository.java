package com.a406.mrm.repository;

import com.a406.mrm.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsByIdAndCode(int id, String code);

    List<Room> findRoomById (@Param("room_id")int room_id);

    @Query(value = "select * from room", nativeQuery = true)
    List<Room> RoomListAll();

    @Query(value = "SELECT * " +
            "FROM room as r " +
            "INNER JOIN todo as t " +
            "ON r.id = t.room_id " +
            "INNER JOIN todo_time as td " +
            "ON t.id = td.todo_id ", nativeQuery = true)
    List<Room> RoomListAllTest();

}
