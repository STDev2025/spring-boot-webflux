package com.rmg.springbootwebflux.repository;

import com.rmg.springbootwebflux.models.documents.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    // Method Query: findByAgeGreaterThan
    Flux<User> findByAgeGreaterThan(int age, Pageable pageable);

    // Custom Query with Pagination and Specific Fields
    @Query(value = "{ 'age': { $gt: ?0 } }", fields = "{ 'name': 1, 'email': 1 }")
    Flux<User> findUsersWithAgeGreaterThan(int age, Pageable pageable);

}
