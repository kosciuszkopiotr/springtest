package com.visionmate.springtest.domain.repositories;

import com.visionmate.springtest.domain.role.entity.RoleEntity;
import com.visionmate.springtest.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Collection<UserEntity> findByRole(RoleEntity roleEntity);

}
