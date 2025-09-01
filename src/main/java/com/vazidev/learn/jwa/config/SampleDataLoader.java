package com.vazidev.learn.jwa.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vazidev.learn.jwa.enumerators.ProductStatus;
import com.vazidev.learn.jwa.models.Product;
import com.vazidev.learn.jwa.repositories.ProductDAO;

@Configuration
public class SampleDataLoader {

    @Bean
    CommandLineRunner loadSampleProducts(ProductDAO productDAO) {
        return args -> {
            if (productDAO.count() > 0) {
                return; // keep existing data
            }
            Product p1 = new Product();
            p1.setProductName("Acme Widget");
            p1.setQuantityOnHand(100);
            p1.setPrice(1999);
            p1.setStatus(ProductStatus.PRODUCT_ACTIVE);

            Product p2 = new Product();
            p2.setProductName("Roadrunner Rocket Skates");
            p2.setQuantityOnHand(25);
            p2.setPrice(8999);
            p2.setStatus(ProductStatus.PRODUCT_DISCOUNTED);

            Product p3 = new Product();
            p3.setProductName("Coyote Anvil");
            p3.setQuantityOnHand(5);
            p3.setPrice(4999);
            p3.setStatus(ProductStatus.PRODUCT_ON_HOLD);

            productDAO.saveAll(Arrays.asList(p1, p2, p3));
        };
    }
}

