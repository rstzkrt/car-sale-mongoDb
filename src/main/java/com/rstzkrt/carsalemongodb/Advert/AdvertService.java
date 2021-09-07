package com.rstzkrt.carsalemongodb.Advert;


import com.rstzkrt.carsalemongodb.Advert.elasticSearch.EsAdvert;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.List;

public interface AdvertService {

    AdvertResponseDTO getAdvert(String advertId);

    List<AdvertResponseDTO> getAdverts();

    List<AdvertResponseDTO> getAdvertsByBrand(String brand);


    Advert saveOrUpdateAdvert(Advert advert);

    void removeAdvert(String id);

    List<AdvertResponseDTO> searchHitsToAdvertDTO(List<SearchHit<EsAdvert>> searchHits);
}
