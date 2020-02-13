package com.laurentiu.spring.thymeleaf.service;

import com.laurentiu.spring.thymeleaf.model.Product;
import com.laurentiu.spring.thymeleaf.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        logger.info("finding all product");
        return productRepository.findAll();
    }

    @Transactional
    public void save(Product product) {
        logger.info("saving product: {}, product");
        productRepository.save(product);
    }

    public Product findById(Long id) {
        logger.info("finding product by id");
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found"));
    }

    public void delete(Long id) {
        logger.info("deleting product with id {},product");
        productRepository.deleteById(id);
    }


}
