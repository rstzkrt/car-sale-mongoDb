package com.rstzkrt.carsalemongodb.User;

import com.rstzkrt.carsalemongodb.Advert.Advert;
import com.rstzkrt.carsalemongodb.Advert.AdvertRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final AdvertRepository advertRepository;

    private final UserRepository userRepository;

    public UserServiceImpl(AdvertRepository advertRepository, UserRepository userRepository) {
        this.advertRepository = advertRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    @Override
    public int deleteUser(String userId){
    User user=userRepository.findById(userId).orElse(null);
    if (!(user == null)){
        List<Advert> adverts=advertRepository.findAllByPostedBy(new ObjectId(user.getId()));
        advertRepository.deleteAll(adverts);
        userRepository.delete(user);
    return 1;
    }else
    return 0;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}