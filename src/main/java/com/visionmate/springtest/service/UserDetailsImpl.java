package com.visionmate.springtest.service;

import com.visionmate.springtest.domain.user.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final String SPRING_ROLE_PREFIX = "ROLE_";

    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl() {}

    public UserDetailsImpl(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.grantedAuthorities = userEntity.getRole().getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(SPRING_ROLE_PREFIX + permission))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
