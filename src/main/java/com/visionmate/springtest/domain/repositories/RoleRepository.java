package com.visionmate.springtest.domain.repositories;

import com.visionmate.springtest.domain.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

}
