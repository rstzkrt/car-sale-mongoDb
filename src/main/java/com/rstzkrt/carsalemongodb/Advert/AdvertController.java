package com.rstzkrt.carsalemongodb.Advert;

import com.rstzkrt.carsalemongodb.Advert.elasticSearch.EsAdvert;
import com.rstzkrt.carsalemongodb.Advert.elasticSearch.EsAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RepositoryRestController
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private EsAdvertService esAdvertService;

    @GetMapping("adverts")
    public ResponseEntity<Object> getAdverts() {
        List<AdvertResponseDTO>advertResponseDTOList =advertService.getAdverts();
        return ResponseEntity.status(200).body(advertResponseDTOList);
    }

    @GetMapping("adverts/{advertId}")
    public ResponseEntity<Object> getAdvert(@PathVariable("advertId") String advertId) {
        AdvertResponseDTO advertResponseDTO=advertService.getAdvert(advertId);
        return ResponseEntity.status(200).body(advertResponseDTO);
    }

    @GetMapping("adverts/findByBrand/{brand}")
    public ResponseEntity<Object> getAdvertByBrand(@PathVariable("brand") String brand) {
        List<AdvertResponseDTO>advertResponseDTOList =advertService.getAdvertsByBrand(brand);
        return ResponseEntity.status(200).body(advertResponseDTOList);
    }


    @GetMapping("/adverts/elastic-search/{keyword}")
    public ResponseEntity<Object> searchDeals( @PathVariable( "keyword") String keyword, Pageable pageable) {
        List<AdvertResponseDTO>  advertResponseDTOList=null;
        if (ObjectUtils.isEmpty(keyword)) {
            return ResponseEntity.status(200).body(Collections.emptyList());
        } else {
            final List<SearchHit<EsAdvert>> searchHits = esAdvertService.queryAdverts(keyword, pageable);
            advertResponseDTOList=advertService.searchHitsToAdvertDTO(searchHits);
            return ResponseEntity.status(200).body(advertResponseDTOList);
        }
    }

    @PostMapping("/adverts")
    public ResponseEntity<Object> saveOrUpdateDeal(@RequestBody Advert advert) {
        Advert response = advertService.saveOrUpdateAdvert(advert);

        if (response == null) {
            return ResponseEntity.status(400).body(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(200).body(advert);
    }

    @DeleteMapping("/adverts/{advertId}")
    public ResponseEntity<Object> removeDeal(@PathVariable("advertId") String advertId) {
        advertService.removeAdvert(advertId);
        return ResponseEntity.status(200).body(HttpStatus.OK);
    }

}