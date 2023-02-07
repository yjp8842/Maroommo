package com.a406.mrm.repository;

import com.a406.mrm.model.entity.UserMemo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserMemoRepository extends MongoRepository<UserMemo, String> {
    UserMemo findByRoomId(int roomId);
}