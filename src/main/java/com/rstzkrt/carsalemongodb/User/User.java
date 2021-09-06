package com.rstzkrt.carsalemongodb.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rstzkrt.carsalemongodb.Advert.Advert;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private String role;

    @URL
    private String avatar;

    @DateTimeFormat
    private Instant dateOfBirth;

    private Map<String, Boolean> favorites = new HashMap<>();

    private List<Advert> myAdverts=new ArrayList<>();
}