package com.rstzkrt.carsalemongodb.Advert;


import java.util.List;

public interface AdvertService {

    AdvertResponseDTO getAdvert(String advertId);

    List<AdvertResponseDTO> getAdverts();

    List<AdvertResponseDTO> getAdvertsByBrand(String brand);
}
