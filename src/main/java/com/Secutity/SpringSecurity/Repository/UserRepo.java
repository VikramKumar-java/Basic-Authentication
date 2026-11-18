package com.Secutity.SpringSecurity.Repository;

import com.Secutity.SpringSecurity.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNameAndIsActive(String name, boolean isActive);
}
