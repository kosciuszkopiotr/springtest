package com.visionmate.springtest.api;

import com.visionmate.springtest.domain.user.entity.UserEntity;

public class UserDTO {

    public String username;
    public String password;
    public int roleId;

    public UserDTO() { }

    public UserDTO(UserEntity userEntity) {
        this.password = userEntity.getPassword();
        this.username = userEntity.getUsername();
        this.roleId = userEntity.getRole().getId();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRoleId() {
        return roleId;
    }
}
