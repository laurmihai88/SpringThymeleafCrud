package com.laurentiu.spring.thymeleaf.config;

import com.laurentiu.spring.thymeleaf.model.Product;
import com.laurentiu.spring.thymeleaf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Autowired
    public ProductRepository productRepository;

    @Bean
    public CommandLineRunner dataLoad() {
        return args -> {
            Product product = new Product();
            product.setName("gogosari");
            product.setPrice(23);
            productRepository.save(product);
        };
    }
}
