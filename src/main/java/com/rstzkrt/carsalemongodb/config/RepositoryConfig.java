package com.rstzkrt.carsalemongodb.config;

import com.rstzkrt.carsalemongodb.Advert.Advert;
import com.rstzkrt.carsalemongodb.User.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors) {
        config.exposeIdsFor(Advert.class, User.class);
    }

}