package com.visionmate.springtest.service;

import com.visionmate.springtest.api.RoleDTO;
import com.visionmate.springtest.api.UserBrowseDTO;
import com.visionmate.springtest.api.UserDTO;
import com.visionmate.springtest.domain.role.entity.RoleEntity;
import com.visionmate.springtest.domain.user.entity.UserEntity;

public class Mapper {

    public static RoleDTO mapToRoleDTO(RoleEntity roleEntity) {
        return RoleDTO.builder()
                .withId(roleEntity.getId())
                .withName(roleEntity.getName())
                .withPermissions(roleEntity.getPermissions())
                .build();
    }

    public static UserBrowseDTO mapToUserBrowseDTO(UserEntity user) {
        return UserBrowseDTO.builder()
                .withId(user.getId())
                .withUsername(user.getUsername())
                .withRole(mapToRoleDTO(user.getRole()))
                .build();
    }

    public static UserEntity mapToUserEntity(UserDTO userDTO, RoleEntity roleEntity)   {
        return new UserEntity()
                .setUsername(userDTO.getUsername())
                .setPassword(userDTO.getPassword())
                .setRole(roleEntity);
    }

}
