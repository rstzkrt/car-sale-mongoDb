package com.rstzkrt.carsalemongodb.User;

import com.rstzkrt.carsalemongodb.Advert.Advert;
import com.rstzkrt.carsalemongodb.Advert.AdvertRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AdvertRepository advertRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public int deleteUser(String userId){
    User user=userRepository.findById(userId).orElse(null);
    if (!(user == null)){
        List<Advert> adverts=advertRepository.findAllByPostedBy(new ObjectId(user.getId()));
        advertRepository.deleteAll(adverts);
        userRepository.delete(user);
    return 1;
    }
    return 0;
    }


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