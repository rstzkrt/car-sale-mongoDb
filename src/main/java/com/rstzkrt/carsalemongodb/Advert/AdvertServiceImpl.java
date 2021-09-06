package com.rstzkrt.carsalemongodb.Advert;

import com.rstzkrt.carsalemongodb.User.User;
import com.rstzkrt.carsalemongodb.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {


    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AdvertResponseDTO getAdvert(String advertId) {
        Advert advert = advertRepository.findById(advertId).orElse(null);
        AdvertResponseDTO responseDTO=null;
        if (!(advert == null)) {
            User user = userRepository.findById(String.valueOf(advert.getPostedBy())).orElse(null);
            responseDTO= new AdvertResponseDTO(
                    advert.getId(), advert.getPostedBy(), advert.getDescription(), advert.getTitle(),
                    advert.getPrice(), advert.getPostDate(), advert.getCity(), advert.getCoverPhoto(),
                    advert.getCarBrand(), advert.getCarTransmission(), advert.getCarMileage(), advert.getCarBodyType(),
                    advert.getCarFuelType(), advert.getCarCondition(), advert.getPhotos(), user);
        }
        return responseDTO;
    }

    @Override
    public List<AdvertResponseDTO> getAdverts() {
        List<Advert> adverts = advertRepository.findAll();
        List<AdvertResponseDTO> advertResponseDTOList = new ArrayList<>();
        for (Advert advert : adverts) {
            User user = userRepository.findById(String.valueOf(advert.getPostedBy())).orElse(null);
            AdvertResponseDTO responseDTO = new AdvertResponseDTO(
                    advert.getId(), advert.getPostedBy(), advert.getDescription(), advert.getTitle(),
                    advert.getPrice(), advert.getPostDate(), advert.getCity(), advert.getCoverPhoto(),
                    advert.getCarBrand(), advert.getCarTransmission(), advert.getCarMileage(), advert.getCarBodyType(),
                    advert.getCarFuelType(), advert.getCarCondition(), advert.getPhotos(), user);
            advertResponseDTOList.add(responseDTO);
        }
        return advertResponseDTOList;
    }


    @Override
    public List<AdvertResponseDTO> getAdvertsByBrand(String brand) {
        List<Advert> advertsByBrand=advertRepository.findAllByCarBrand(brand);
        List<AdvertResponseDTO> advertResponseDTOList=new ArrayList<>();
        for(Advert advert:advertsByBrand){
            User user=userRepository.findById(String.valueOf(advert.getPostedBy())).orElse(null);
            AdvertResponseDTO advertResponseDTO=new AdvertResponseDTO(
                    advert.getId(), advert.getPostedBy(), advert.getDescription(), advert.getTitle(),
                    advert.getPrice(), advert.getPostDate(), advert.getCity(), advert.getCoverPhoto(),
                    advert.getCarBrand(), advert.getCarTransmission(), advert.getCarMileage(), advert.getCarBodyType(),
                    advert.getCarFuelType(), advert.getCarCondition(), advert.getPhotos(), user);

            advertResponseDTOList.add(advertResponseDTO);
        }
        return advertResponseDTOList;
    }
}
