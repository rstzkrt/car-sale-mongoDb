package com.rstzkrt.carsalemongodb.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("integration-test")
@AutoConfigureJsonTesters
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<User> json;

    private final User user=new User("123456789","example@example.com","https://avatar-avatar");

    @AfterEach
    void clear() {
        mongoTemplate.dropCollection("users");
    }

    @Test
    public void shouldCreateUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(json.write(user).getJson())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.email").value("example@example.com"))
                .andExpect(jsonPath("$.uid").value("123456789"))
                .andExpect(jsonPath("$.avatar").value("https://avatar-avatar"));
    }

    @Test
    public void shouldReturnOneUserInArray() throws Exception {
        mongoTemplate.insert(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$._embedded.users", hasSize(1)))
                .andExpect(jsonPath("$._embedded.users[0].email").value(user.getEmail()))
                .andExpect(jsonPath("$._embedded.users[0].uid").value("123456789"))
                .andExpect(jsonPath("$._embedded.users[0].avatar").value("https://avatar-avatar"));
    }

    @Test
    public void shouldReturnEmptyArray() throws Exception {

        mongoTemplate.dropCollection("users");
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$._embedded.users", hasSize(1)))
                .andExpect(jsonPath("$._embedded.users[0].email").value(user.getEmail()))
                .andExpect(jsonPath("$._embedded.users[0].uid").value("123456789"))
                .andExpect(jsonPath("$._embedded.users[0].avatar").value("https://avatar-avatar"));
    }
}