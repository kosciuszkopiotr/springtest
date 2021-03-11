package com.visionmate.springtest.service;

import com.visionmate.springtest.domain.repositories.RoleRepository;
import com.visionmate.springtest.domain.repositories.UserRepository;
import com.visionmate.springtest.domain.role.entity.RoleEntity;
import com.visionmate.springtest.domain.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "admin";
    private static final String DEFAULT_ADMIN_ROLE = "ADMIN";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> userDetails = userRepository.findByUsername(username)
                .map(this::mapToUserDetails);
        if (userDetails.isEmpty() && DEFAULT_ADMIN_USERNAME.equals(username)) {
            return mapToUserDetails(createDefaultAdminUser());
        }
        return userDetails.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }

    private UserEntity createDefaultAdminUser() {
        RoleEntity roleEntity = roleRepository.findByName(DEFAULT_ADMIN_ROLE).orElseGet(() -> new RoleEntity(DEFAULT_ADMIN_ROLE).setPermissions(DEFAULT_ADMIN_ROLE));
        UserEntity userEntity = new UserEntity(DEFAULT_ADMIN_USERNAME, DEFAULT_ADMIN_PASSWORD)
                .setRole(roleEntity);
        return userRepository.save(userEntity);
    }

    private UserDetails mapToUserDetails(UserEntity user) {
        return new UserDetailsImpl(user);
    }
}
