package com.visionmate.springtest.resource;

import com.visionmate.springtest.api.RoleDTO;
import com.visionmate.springtest.entity.RoleEntity;
import com.visionmate.springtest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/vis-test/roles")
public class RoleResource {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public List<RoleDTO> getRoles() {
        return roleRepository.findAll().stream()
                .map(this::mapToRoleDTO)
                .collect(Collectors.toList());
    }

    private RoleDTO mapToRoleDTO(RoleEntity roleEntity) {
        return new RoleDTO(roleEntity.getId(), roleEntity.getName(), roleEntity.getPermissions());
    }

    @PostMapping("")
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        RoleEntity createdRole = roleRepository.save(mapToRoleEntity(roleDTO));
        return mapToRoleDTO(createdRole);
    }

    @PutMapping("/{id}")
    public RoleDTO updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
        RoleEntity roleEntity = mapToRoleEntity(roleDTO);
        RoleEntity updated = roleRepository.save(roleEntity);
        return mapToRoleDTO(updated);
    }

    private RoleEntity mapToRoleEntity(RoleDTO roleDTO) {
        return new RoleEntity(roleDTO.getId(), roleDTO.getName(), String.join(",", roleDTO.getPermissions()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        if ( roleRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        roleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
