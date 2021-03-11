package com.visionmate.springtest.domain.role.resource;

import com.visionmate.springtest.api.RoleDTO;
import com.visionmate.springtest.domain.repositories.RoleRepository;
import com.visionmate.springtest.domain.repositories.UserRepository;
import com.visionmate.springtest.domain.role.entity.RoleEntity;
import com.visionmate.springtest.domain.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/vis-test/roles")
public class RoleResource {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<RoleDTO> getRoles() {
        return roleRepository.findAll().stream()
                .map(this::mapToRoleDTO)
                .collect(Collectors.toList());
    }

    private RoleDTO mapToRoleDTO(RoleEntity roleEntity) {
        return RoleDTO.builder()
                .withId(roleEntity.getId())
                .withName(roleEntity.getName())
                .withPermissions(roleEntity.getPermissions())
                .build();
    }

    @PostMapping("")
    @RolesAllowed({"CREATE_ROLE", "ADMIN"})
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        RoleEntity createdRole = roleRepository.save(mapToRoleEntity(roleDTO));
        return mapToRoleDTO(createdRole);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"UPDATE_ROLE", "ADMIN"})
    public ResponseEntity<?> updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
        Optional<RoleEntity> foundRole = roleRepository.findById(id);
        if (foundRole.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        RoleEntity updatingRoleEntity = mapToRoleEntity(roleDTO);
        foundRole.get().setPermissions(updatingRoleEntity.getPermissions());
        RoleEntity updated = roleRepository.save(foundRole.get());
        return ResponseEntity.ok(mapToRoleDTO(updated));
    }

    private RoleEntity mapToRoleEntity(RoleDTO roleDTO) {
        return new RoleEntity(roleDTO.getId(), roleDTO.getName(), String.join(",", roleDTO.getPermissions()));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"DELETE_ROLE", "ADMIN"})
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        Optional<RoleEntity> role = roleRepository.findById(id);
        if (role.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Collection<UserEntity> byRole = userRepository.findByRole(role.get());
        if (!byRole.isEmpty()) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("Cannot delete, there are users assigned to role");
        }
        roleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}