package com.rstzkrt.carsalemongodb.User;

import com.rstzkrt.carsalemongodb.Advert.Advert;

import java.util.List;

public interface UserService {

    List<Advert> getFavorites();

    User favorite(String advertId) throws Exception;

    User unfavorite(String advertId) throws Exception;
}
