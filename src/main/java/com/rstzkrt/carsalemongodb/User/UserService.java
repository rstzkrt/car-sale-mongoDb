package com.rstzkrt.carsalemongodb.User;

import com.rstzkrt.carsalemongodb.Advert.Advert;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    @Transactional
    int deleteUser(String userId);

    List<Advert> getFavorites();

    User favorite(String advertId) throws Exception;

    User unfavorite(String advertId) throws Exception;
}
