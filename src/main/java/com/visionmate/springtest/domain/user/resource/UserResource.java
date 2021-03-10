package com.visionmate.springtest.domain.user.resource;

import com.visionmate.springtest.api.UserBrowseDTO;
import com.visionmate.springtest.api.UserDTO;
import com.visionmate.springtest.domain.repositories.RoleRepository;
import com.visionmate.springtest.domain.repositories.UserRepository;
import com.visionmate.springtest.domain.role.entity.RoleEntity;
import com.visionmate.springtest.domain.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/vis-test/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public List<UserBrowseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserBrowseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("")
    public UserBrowseDTO createUser(@RequestBody UserDTO userDTO) {
        RoleEntity roleEntity = roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> new RuntimeException("Role with ID " + userDTO.getRoleId() + " not found"));
        UserEntity createdUser = userRepository.save(new UserEntity(userDTO, roleEntity));
        return new UserBrowseDTO(createdUser);
    }

    @PutMapping("/{id}")
    public UserBrowseDTO updateUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDTO.getUsername());
        user.setPassword(user.getPassword());
        user.setRole(roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found")));
        UserEntity updatedUser = userRepository.save(user);
        return new UserBrowseDTO(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        if (userRepository.findById(userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(userId);

        return ResponseEntity.ok().build();
    }

}
