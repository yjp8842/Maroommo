package com.a406.mrm.repository;

import com.test.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findById(String id);
}
