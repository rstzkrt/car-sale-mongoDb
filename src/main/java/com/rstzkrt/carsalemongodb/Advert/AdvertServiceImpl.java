package com.rstzkrt.carsalemongodb.Advert;

import com.rstzkrt.carsalemongodb.Advert.elasticSearch.EsAdvert;
import com.rstzkrt.carsalemongodb.Advert.elasticSearch.EsAdvertRepository;
import com.rstzkrt.carsalemongodb.User.User;
import com.rstzkrt.carsalemongodb.User.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {


    private final AdvertRepository advertRepository;

    private final UserRepository userRepository;

    private final EsAdvertRepository esAdvertRepository;

    public AdvertServiceImpl(AdvertRepository advertRepository, UserRepository userRepository, EsAdvertRepository esAdvertRepository) {
        this.advertRepository = advertRepository;
        this.userRepository = userRepository;
        this.esAdvertRepository = esAdvertRepository;
    }

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

    @Transactional
    @Override
    public Advert saveOrUpdateAdvert(Advert advert) {
        Advert tempAdvert = advertRepository.save(advert);
        User user=userRepository.findById(String.valueOf(advert.getPostedBy())).orElse(null);
        AdvertResponseDTO responseDTO=new AdvertResponseDTO(
                advert.getId(), advert.getPostedBy(), advert.getDescription(), advert.getTitle(),
                advert.getPrice(), advert.getPostDate(), advert.getCity(), advert.getCoverPhoto(),
                advert.getCarBrand(), advert.getCarTransmission(), advert.getCarMileage(), advert.getCarBodyType(),
                advert.getCarFuelType(), advert.getCarCondition(), advert.getPhotos(), user);

        esAdvertRepository.save(new EsAdvert(responseDTO));
        return tempAdvert;
    }

    @Transactional
    @Override
    public void removeAdvert(String id) {
        advertRepository.deleteById(id);
        esAdvertRepository.deleteEsAdvertById(id);
    }

    @Override
    public List<AdvertResponseDTO> searchHitsToAdvertDTO(List<SearchHit<EsAdvert>> searchHits) {
        List<AdvertResponseDTO> advertResponseDTOList=new ArrayList<>();
        for(SearchHit<EsAdvert> esAdvertSearchHit:searchHits){
            ObjectId userid=esAdvertSearchHit.getContent().getPostedById();
            User user=userRepository.findById(String.valueOf(userid)).orElse(null);
            Advert advert=advertRepository.findById(esAdvertSearchHit.getContent().getId()).orElse(null);

            AdvertResponseDTO responseDTO=new AdvertResponseDTO(
                    advert.getId(), advert.getPostedBy(), advert.getDescription(), advert.getTitle(),
                    advert.getPrice(), advert.getPostDate(), advert.getCity(), advert.getCoverPhoto(),
                    advert.getCarBrand(), advert.getCarTransmission(), advert.getCarMileage(), advert.getCarBodyType(),
                    advert.getCarFuelType(), advert.getCarCondition(), advert.getPhotos(), user);

            advertResponseDTOList.add(responseDTO);
        }
        return advertResponseDTOList;
    }
}