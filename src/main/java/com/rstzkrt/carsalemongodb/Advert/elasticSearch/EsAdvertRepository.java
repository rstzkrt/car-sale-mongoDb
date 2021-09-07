package com.rstzkrt.carsalemongodb.Advert.elasticSearch;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsAdvertRepository extends ElasticsearchRepository<EsAdvert, String> {



    //@Query("{\"bool\": {\"must\": [{\"match\": {\"title\": \"?0\"}}]}}")
    @Query("{\"multi_match\": {\"query\": \"?0\",\"fields\": [\"title^1.0\",\"description^1.0\" , \"city^1.0\",\"carBrand^1.0\"],\"type\": \"phrase_prefix\",\"operator\": \"OR\",\"slop\": 0,\"prefix_length\": 0,\"max_expansions\": 50,\"zero_terms_query\": \"NONE\",\"auto_generate_synonyms_phrase_query\": true,\"fuzzy_transpositions\": true,\"boost\": 1}}")
    List<SearchHit<EsAdvert>> queryDeals(String keyword, Pageable pageable);

    void deleteEsAdvertById(String id);

}