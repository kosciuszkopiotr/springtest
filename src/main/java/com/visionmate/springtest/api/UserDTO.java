package com.visionmate.springtest.api;

public class UserDTO {

    private String username;
    private String password;
    private int roleId;

    public UserDTO() {}

    private UserDTO(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.roleId = builder.roleId;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String username;
        private String password;
        //String rolename?
        private int roleId;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRoleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }

    }

}
