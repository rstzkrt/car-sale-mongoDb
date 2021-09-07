package com.rstzkrt.carsalemongodb.Advert.elasticSearch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rstzkrt.carsalemongodb.Advert.Advert;
import com.rstzkrt.carsalemongodb.Advert.AdvertResponseDTO;
import com.rstzkrt.carsalemongodb.User.User;
import com.rstzkrt.carsalemongodb.util.ObjectIdJsonSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(indexName = "advert")
@TypeAlias("advert")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class EsAdvert {

    @Id
    private String id;

    private ObjectId postedById;

    private String description;

    private String title;

    private Double price;

    private String city;

    private String carBrand;


    public EsAdvert(AdvertResponseDTO advertResponseDTO) {
        this.id = advertResponseDTO.getId();
        this.title = advertResponseDTO.getTitle();
        this.description = advertResponseDTO.getDescription();
        this.carBrand=advertResponseDTO.getCarBrand();
        this.postedById = advertResponseDTO.getPostedById();
        this.price=advertResponseDTO.getPrice();
        this.city=advertResponseDTO.getCity();
    }
}