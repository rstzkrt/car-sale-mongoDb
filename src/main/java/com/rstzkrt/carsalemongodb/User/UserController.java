package com.rstzkrt.carsalemongodb.User;

import com.rstzkrt.carsalemongodb.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RepositoryRestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @DeleteMapping("users/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable("userId") String userId){
        int deleted=userService.deleteUser(userId);

        if (deleted==0) {
            return ResponseEntity.status(404).body(HttpStatus.BAD_REQUEST);
        }else
            return ResponseEntity.status(201).body(deleted);

    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User response = userService.create(user);

        if (response == null) {
            return ResponseEntity.status(400).body(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(201).body(response);
    }

    //user detail page
    @GetMapping("/users/me")
    public ResponseEntity<Object> getAuthenticatedUser() {
        User response = securityService.getUser();

        if (response == null) {
            return ResponseEntity.status(400).body(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(200).body(response);
    }
}