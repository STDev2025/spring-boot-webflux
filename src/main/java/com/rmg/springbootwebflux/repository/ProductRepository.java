package com.rmg.springbootwebflux.repository;

import com.rmg.springbootwebflux.models.documents.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
