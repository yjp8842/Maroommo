package com.a406.mrm.repository;

import com.a406.mrm.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(String id);
    boolean existsById(String id);
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
