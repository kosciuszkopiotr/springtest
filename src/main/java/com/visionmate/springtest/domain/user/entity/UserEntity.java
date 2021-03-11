package com.visionmate.springtest.domain.user.entity;

import com.visionmate.springtest.domain.role.entity.RoleEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    private RoleEntity role;

    public UserEntity() {}

    public UserEntity (String username, String password) {
        if (Strings.isBlank(username) || Strings.isBlank(password)) {
            throw new IllegalArgumentException("Username/password cannot be empty");
        }
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
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

    public UserEntity setId(int id) {
        this.id = id;
        return this;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
        return this;
    }

    public UserEntity setRole(RoleEntity role) {
        this.role = role;
        return this;
    }

}
