package com.visionmate.springtest.api;

import com.visionmate.springtest.entity.RoleEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoleDTO {

    private int id;
    private String name;
    private List<String> permissions;

    public RoleDTO(int id, String name, String permissions) {
        this.id = id;
        this.name = name;
        this.permissions = Arrays.stream(permissions.split(","))
                .collect(Collectors.toList());
    }

    public RoleDTO(RoleEntity roleEntity) {
        this.id = roleEntity.getId();
        this.name = roleEntity.getName();
        this.permissions = Arrays.stream(roleEntity.getPermissions().split(","))
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getPermissions() {
        return permissions;
    }



}
