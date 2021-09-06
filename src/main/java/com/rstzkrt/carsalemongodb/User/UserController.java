package com.rstzkrt.carsalemongodb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestController
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("users/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable("userId") String userId){
        int deleted=userService.deleteUser(userId);

        if (deleted==0) {
            return ResponseEntity.status(404).body(HttpStatus.BAD_REQUEST);
        }else
            return ResponseEntity.status(201).body(deleted);

    }
}