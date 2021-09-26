package com.rstzkrt.carsalemongodb.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

@DataMongoTest
@ActiveProfiles("integration-test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final User user=new User("123456789","example@example.com","https://avatar-avatar");

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }
    @BeforeEach
    void insertDatabase(){
        userRepository.save(user);
    }

    @Test
    void findByUidShouldReturnUser() {

        Optional<User> expectedUser=userRepository.findByUid("123456789");
        assertEquals(Optional.of(user),expectedUser);
    }

    @Test
    void shouldReturnNullIfUserWithUidDoesntExist() {

        Optional<User> expectedUser=userRepository.findByUid("unknown");

        assertNull(expectedUser);
    }

}