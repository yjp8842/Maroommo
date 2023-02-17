package com.a406.mrm.repository;

import com.a406.mrm.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByProviderAndProviderId(String provider, String providerId);
    Optional<User> findByNameAndEmail(String name, String email);
    boolean existsByIdAndNameAndEmail(String id, String name, String email);
}