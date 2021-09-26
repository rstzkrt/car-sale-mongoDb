package com.rstzkrt.carsalemongodb.User;

import com.rstzkrt.carsalemongodb.Advert.AdvertRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataMongoTest
class UserServiceImplTest {

    @MockBean
    private AdvertRepository advertRepository;

    @MockBean
    private UserRepository userRepository;

    private final UserService userService=new UserServiceImpl(advertRepository,userRepository);

    private final User user=new User("123456789","example@example.com","https://avatar-avatar");

    @Test
    void shouldCreateUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User newUser=userService.create(user);

        assertEquals(newUser, user);
    }
}