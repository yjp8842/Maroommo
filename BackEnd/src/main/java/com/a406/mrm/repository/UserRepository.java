package com.a406.mrm.repository;

import com.a406.mrm.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findById(String id);
    public boolean existsById(String id);
}
