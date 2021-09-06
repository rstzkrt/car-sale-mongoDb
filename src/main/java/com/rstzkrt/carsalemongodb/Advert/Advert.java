package com.rstzkrt.carsalemongodb.Advert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rstzkrt.carsalemongodb.util.ObjectIdJsonSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "adverts")
@TypeAlias("advert")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Advert {

    @Id
    private String id;

    @Indexed
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId postedBy;

    @Indexed
    private String description;

    @Indexed
    private String title;

    private Double price;

    @CreatedDate
    private Instant postDate;

    private String city;

    @URL
    private String coverPhoto;

    @Indexed
    private String carBrand;

    private String carTransmission;

    private Double carMileage;

    private String carBodyType;

    private String carFuelType;

    private String carCondition;

    private List<String> photos = new ArrayList<>();

    public Advert(String description,
                  String title,
                  Double price,
                  Instant postDate,
                  String city,
                  String coverPhoto,
                  String car_brand,
                  String car_transmission,
                  Double car_mileage,
                  String car_bodyType,
                  String car_fuelType,
                  String car_condition,
                  List<String> photos) {
        this.description = description;
        this.title = title;
        this.price = price;
        this.postDate = postDate;
        this.city = city;
        this.coverPhoto = coverPhoto;
        this.carBrand = car_brand;
        this.carTransmission = car_transmission;
        this.carMileage = car_mileage;
        this.carBodyType = car_bodyType;
        this.carFuelType = car_fuelType;
        this.carCondition = car_condition;
        this.photos = photos;
    }
    public Advert(String description,
                  String title,
                  Double price,
                  Instant postDate,
                  String city,
                  String coverPhoto,
                  String car_brand,
                  String car_transmission,
                  Double car_mileage,
                  String car_bodyType,
                  String car_fuelType,
                  String car_condition) {
        this.description = description;
        this.title = title;
        this.price = price;
        this.postDate = postDate;
        this.city = city;
        this.coverPhoto = coverPhoto;
        this.carBrand = car_brand;
        this.carTransmission = car_transmission;
        this.carMileage = car_mileage;
        this.carBodyType = car_bodyType;
        this.carFuelType = car_fuelType;
        this.carCondition = car_condition;
    }
}