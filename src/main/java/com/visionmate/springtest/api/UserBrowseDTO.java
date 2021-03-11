package com.visionmate.springtest.api;

public class UserBrowseDTO {

    private int id;
    private String username;
    private RoleDTO role;

    public UserBrowseDTO() {}

    private UserBrowseDTO(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.role = builder.role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public RoleDTO getRole() {
        return role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int id;
        private String username;
        private RoleDTO role;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withRole(RoleDTO role) {
            this.role = role;
            return this;
        }

        public UserBrowseDTO build() {
            return new UserBrowseDTO(this);
        }

    }

}
