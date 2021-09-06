package com.rstzkrt.carsalemongodb.Advert;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rstzkrt.carsalemongodb.User.User;

import com.rstzkrt.carsalemongodb.util.ObjectIdJsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertResponseDTO {

    private String id;

    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId postedById;

    private String description;

    private String title;

    private Double price;

    @CreatedDate
    private Instant postDate;

    private String city;

    private String coverPhoto;

    private String carBrand;

    private String carTransmission;

    private Double carMileage;

    private String carBodyType;

    private String carFuelType;

    private String carCondition;

    private List<String> photos = new ArrayList<>();

    private User postedByUser;
}
