package com.rstzkrt.carsalemongodb.User;


import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional
    int deleteUser(String userId);

    User create(User user);

}
