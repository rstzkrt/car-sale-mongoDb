package com.rstzkrt.carsalemongodb.Advert.elasticSearch;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EsAdvertServiceImpl implements EsAdvertService {


    @Autowired
    private EsAdvertRepository esAdvertRepository;

    @Override
    public void deleteByAdvertId(String id) {
        esAdvertRepository.deleteEsAdvertById(id);
    }


    @Override
    public EsAdvert saveOrUpdate(EsAdvert esAdvert) {
        return esAdvertRepository.save(esAdvert);
    }

    @Override
    public Page<EsAdvert> findAll(Pageable pageable) {
        return esAdvertRepository.findAll(pageable);
    }

    @Override
    public List<SearchHit<EsAdvert>> queryAdverts(String keyword, Pageable pageable) {
        return esAdvertRepository.queryDeals(keyword, pageable);
    }
}
