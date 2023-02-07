package com.a406.mrm.repository;

import com.a406.mrm.model.entity.RoomMemo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomMemoRepository extends MongoRepository<RoomMemo, String> {
    RoomMemo findByRoomId(int roomId);
}
