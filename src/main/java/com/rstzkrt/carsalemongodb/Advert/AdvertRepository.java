package com.rstzkrt.carsalemongodb.Advert;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "adverts", path = "adverts")
public interface AdvertRepository extends MongoRepository<Advert,String> {

}