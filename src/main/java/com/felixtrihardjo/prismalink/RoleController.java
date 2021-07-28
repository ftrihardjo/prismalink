package com.felixtrihardjo.prismalink;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RoleController {

    private RoleService roleService;
    private RoleRepository roleRepository;

    public RoleController(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/role/create")
    public ResponseEntity<Object> createRole(@RequestBody Role role) {
        return  roleService.addRole(role);
    }
    @DeleteMapping("/role/delete/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Integer id) {
        return roleService.deleteRole(id);
    }
    @GetMapping("/role/details/{id}")
    public Role getRole(@PathVariable Integer id) {
        if(roleRepository.findById(id).isPresent())
            return roleRepository.findById(id).get();
        else return null;
    }
    @GetMapping("/role/all")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
    @PutMapping("/role/update/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Integer id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }


}
