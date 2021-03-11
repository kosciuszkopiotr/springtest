package com.visionmate.springtest.api;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RoleDTO {

    private int id;
    private String name;
    private Set<String> permissions;

    public RoleDTO() {}

    private RoleDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.permissions = builder.permissions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getPermissions() {
        return Optional.ofNullable(permissions).orElseGet(Collections::emptySet);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private Set<String> permissions = new HashSet<>();

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPermissions(Set<String> permissions) {
            this.permissions.addAll(permissions);
            return this;
        }

        public RoleDTO build() {
            return new RoleDTO(this);
        }

    }


}
