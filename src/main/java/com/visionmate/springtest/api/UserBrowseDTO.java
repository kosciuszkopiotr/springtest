package com.visionmate.springtest.api;

import com.visionmate.springtest.entity.UserEntity;

public class UserBrowseDTO {

    public int id;
    public String username;
    public RoleDTO role;

    public UserBrowseDTO() {}

    public UserBrowseDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.role = new RoleDTO(userEntity.getRole());
    }

}
