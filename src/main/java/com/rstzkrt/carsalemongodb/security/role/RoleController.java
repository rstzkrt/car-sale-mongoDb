package com.rstzkrt.carsalemongodb.security.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@IsSuper
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PutMapping
    public void addRole(@RequestParam String uid, @RequestParam String role) throws Exception {
        roleService.addRole(uid, role);
    }

    @DeleteMapping
    public void removeRole(@RequestParam String uid, @RequestParam String role) {
        roleService.removeRole(uid, role);
    }

}