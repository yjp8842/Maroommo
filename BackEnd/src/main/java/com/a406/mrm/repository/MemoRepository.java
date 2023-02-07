package com.a406.mrm.repository;

import com.a406.mrm.model.entity.Memo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemoRepository extends MongoRepository<Memo, String> {
    Memo findByRoomId(int roomId);
    Memo findByUserId(String userId);
}