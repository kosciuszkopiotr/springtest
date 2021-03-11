package com.visionmate.springtest.domain.user.resource;

import com.visionmate.springtest.api.UserBrowseDTO;
import com.visionmate.springtest.api.UserDTO;
import com.visionmate.springtest.domain.repositories.RoleRepository;
import com.visionmate.springtest.domain.repositories.UserRepository;
import com.visionmate.springtest.domain.role.entity.RoleEntity;
import com.visionmate.springtest.domain.user.entity.UserEntity;
import com.visionmate.springtest.service.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/vis-test/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    @RolesAllowed({"GET_USER", "ADMIN"})
    public List<UserBrowseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(Mapper::mapToUserBrowseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("")
    @RolesAllowed({"CREATE_USER", "ADMIN"})
    public UserBrowseDTO createUser(@RequestBody UserDTO userDTO) {
        RoleEntity roleEntity = roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> new RuntimeException("Role with ID " + userDTO.getRoleId() + " not found"));
        try {
            UserEntity createdUser = userRepository.save(Mapper.mapToUserEntity(userDTO, roleEntity));
            return Mapper.mapToUserBrowseDTO(createdUser);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @RolesAllowed({"UPDATE_USER", "ADMIN"})
    public UserBrowseDTO updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found")));
        UserEntity updatedUser = userRepository.save(user);
        return Mapper.mapToUserBrowseDTO(updatedUser);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"DELETE_USER", "ADMIN"})
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        Optional<UserEntity> userToDelete = userRepository.findById(id);
        if (userToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userToDelete.get().setRole(null);
        userRepository.delete(userToDelete.get());

        return ResponseEntity.ok().build();
    }

}
