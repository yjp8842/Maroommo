package com.a406.mrm.repository;

import com.a406.mrm.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    static final String UPDATE_USER_LAST_LOGIN = "update users set last_login_time = :lastLoginTime where id = :id";

    @Transactional
    @Modifying
    @Query(value = UPDATE_USER_LAST_LOGIN, nativeQuery = true)
    public int updateUserLastLogin(@Param("id") String id, @Param("lastLoginTime")LocalDateTime lastLoginTime);

    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}