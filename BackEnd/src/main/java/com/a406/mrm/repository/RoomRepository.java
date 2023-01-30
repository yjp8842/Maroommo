package com.a406.mrm.repository;

import com.a406.mrm.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByEntryCode(String entryCode);
    boolean existsByEntryCode(String entryCode);
}
