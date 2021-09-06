package com.rstzkrt.carsalemongodb.Advert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RepositoryRestController
public class AdvertController {

    @Autowired
    private AdvertService advertService;

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
}