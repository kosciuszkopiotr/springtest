package com.visionmate.springtest.domain.role.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "role")
public class RoleEntity {

    private static final String PERMISSIONS_SEPARATOR = ",";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "permissions")
    private String permissions;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getPermissions() {
        return Arrays.stream(permissions.split(PERMISSIONS_SEPARATOR))
                .collect(Collectors.toSet());
    }

    public RoleEntity setId(int id) {
        this.id = id;
        return this;
    }

    public RoleEntity setName(String name) {
        this.name = name;
        return this;
    }

    public RoleEntity setPermissions(String permissions) {
        this.permissions = permissions;
        return this;
    }

    public RoleEntity setPermissions(Collection<String> permissions) {
        this.permissions = String.join(PERMISSIONS_SEPARATOR, permissions);
        return this;
    }

    public RoleEntity() {}

    public RoleEntity(String name) {
        this.name = name;
    }

    public RoleEntity(int id, String name, String permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

}
