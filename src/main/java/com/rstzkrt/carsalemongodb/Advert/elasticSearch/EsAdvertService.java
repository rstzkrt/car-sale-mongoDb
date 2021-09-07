package com.rstzkrt.carsalemongodb.Advert.elasticSearch;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;


public interface EsAdvertService {

    EsAdvert saveOrUpdate(EsAdvert esADvert);

    void deleteByAdvertId(String id);

    Page<EsAdvert> findAll(Pageable pageable);

    List<SearchHit<EsAdvert>> queryAdverts(String keyword, Pageable pageable);
}
