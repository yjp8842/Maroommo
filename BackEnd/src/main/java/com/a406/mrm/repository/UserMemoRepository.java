package com.a406.mrm.repository;

import com.a406.mrm.model.entity.UserMemo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMemoRepository extends MongoRepository<UserMemo, String> {
    UserMemo findByUserId(String userId);
}