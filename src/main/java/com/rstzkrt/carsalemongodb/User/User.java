package com.rstzkrt.carsalemongodb.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "users")
@TypeAlias("user")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank
    private String uid;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    @URL
    private String avatar;

    private Map<String, Boolean> favorites = new HashMap<>();

    public User(String id) {
        this.id = id;
    }

    public User(String uid, String email, String avatar) {
        this.uid = uid;
        this.email = email;
        this.avatar = avatar;
    }
}