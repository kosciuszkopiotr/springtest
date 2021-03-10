package com.visionmate.springtest.entity;

import com.visionmate.springtest.api.RoleDTO;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "permissions")
    private String permissions;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPermissions() {
        return permissions;
    }

    public RoleEntity() {}

    public RoleEntity(int id, String name, String permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

}
