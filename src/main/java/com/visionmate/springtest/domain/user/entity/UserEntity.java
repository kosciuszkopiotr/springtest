package com.visionmate.springtest.domain.user.entity;

import com.visionmate.springtest.api.UserDTO;
import com.visionmate.springtest.domain.role.entity.RoleEntity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role", referencedColumnName = "id")
    private RoleEntity role;

    public UserEntity() {}

    public UserEntity(UserDTO userDTO, RoleEntity roleEntity) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.role = roleEntity;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
