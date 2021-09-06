package com.rstzkrt.carsalemongodb.Advert;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "adverts", path = "adverts")
public interface AdvertRepository extends MongoRepository<Advert,String> {

    List<Advert> findAllByPostedBy(ObjectId postedBy);
}