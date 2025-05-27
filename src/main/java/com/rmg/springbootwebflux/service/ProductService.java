package com.rmg.springbootwebflux.service;

import com.rmg.springbootwebflux.models.documents.Product;
import com.rmg.springbootwebflux.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> createProduct(Product product) {
        product.setCreateDate(Instant.now());
        product.setUpdateDate(Instant.now());
        return productRepository.save(product);
    }

    public Mono<Product> updateQuantity(String id, int quantity) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setQuantity(quantity);
                    product.setUpdateDate(Instant.now());
                    return productRepository.save(product);
                });
    }

    @Transactional
    public Mono<Product> updateQuantityV2(String id, int quantity) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setQuantity(quantity);
                    return productRepository.save(product);
                })
                .doOnSuccess(prod -> System.out.println("Transaction successful"))
                .doOnError(e -> System.err.println("Transaction failed: " + e.getMessage()));
    }

    public Mono<Void> deleteAllProducts() {
        return productRepository.deleteAll();
    }

}

