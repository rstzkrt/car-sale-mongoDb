package com.rstzkrt.carsalemongodb.User;

import com.rstzkrt.carsalemongodb.Advert.Advert;
import com.rstzkrt.carsalemongodb.Advert.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private AdvertRepository advertRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Advert> getFavorites() {
        return null;
    }

    @Override
    public User favorite(String advertId) throws Exception {
        return null;
    }

    @Override
    public User unfavorite(String advertId) throws Exception {
    return null;
    }
}
