package com.rstzkrt.carsalemongodb.Advert;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "adverts", path = "adverts")
public interface AdvertRepository extends MongoRepository<Advert,String> {

    List<Advert> findAllByCarBrand(String brand);

    List<Advert> findAllByPostedBy(ObjectId postedBy);
}