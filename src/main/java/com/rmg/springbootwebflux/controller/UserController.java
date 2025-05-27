package com.rmg.springbootwebflux.controller;

import com.rmg.springbootwebflux.models.documents.User;
import com.rmg.springbootwebflux.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint: Find users by age with pagination
    @GetMapping("/age-greater/{age}")
    public Flux<User> getUsersByAge(
            @PathVariable int age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return userService.findUsersByAge(age, page, size);
    }

    // Endpoint: Find users with specific fields using @Query
    @GetMapping("/age-greater-fields/{age}")
    public Flux<User> getUsersByAgeWithFields(
            @PathVariable int age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return userService.findUsersByAgeWithFields(age, page, size);
    }

    @PostMapping("/create")
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createProduct(user);
    }

}
