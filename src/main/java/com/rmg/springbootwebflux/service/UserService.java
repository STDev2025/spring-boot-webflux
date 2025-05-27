package com.rmg.springbootwebflux.service;

import com.rmg.springbootwebflux.models.documents.Product;
import com.rmg.springbootwebflux.models.documents.User;
import com.rmg.springbootwebflux.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> createProduct(User user) {
        return userRepository.save(user);
    }

    // Find Users by Age Greater Than with Pagination
    public Flux<User> findUsersByAge(int age, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByAgeGreaterThan(age, pageable);
    }

    // Find Users with Specific Fields using @Query
    public Flux<User> findUsersByAgeWithFields(int age, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findUsersWithAgeGreaterThan(age, pageable);
    }

}
