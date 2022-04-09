package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;

@Configuration
public class configRepo implements RepositoryRestConfigurer {
    
    private EntityManager entityManager;

    @Autowired
    public configRepo(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        // RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config,
        // cors);

        HttpMethod[] notsupported = { HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.POST };
        // disabe for product release 1.0
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(notsupported))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(notsupported));
        // disable for product category release 1.0
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(notsupported))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(notsupported));

        exponseId(config);
    }

    private void exponseId(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> classes = new ArrayList<Class>();
        for (EntityType entity : entities) {
            classes.add(entity.getJavaType());
        }
        Class[] DomainType = classes.toArray(new Class[0]);

        config.exposeIdsFor(DomainType);

    }

}
