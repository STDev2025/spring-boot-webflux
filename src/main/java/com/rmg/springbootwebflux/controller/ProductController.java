package com.rmg.springbootwebflux.controller;

import com.rmg.springbootwebflux.models.documents.Product;
import com.rmg.springbootwebflux.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public Mono<Product> create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PatchMapping("/{id}/update-quantity/{qty}")
    public Mono<Product> updateQuantity(@PathVariable String id, @PathVariable int qty) {
        return productService.updateQuantity(id, qty);
    }

    @PatchMapping("/{id}/update-quantity-v2/{quantity}")
    public Mono<ResponseEntity<String>> updateQuantityV2(@PathVariable String id, @PathVariable int quantity) {
        return productService.updateQuantityV2(id, quantity)
                .map(updated -> ResponseEntity.ok("Product updated successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Update failed: " + e.getMessage())));
    }

    @DeleteMapping("/deleteAll")
    public Mono<Void> deleteAllProducts() {
        return productService.deleteAllProducts();
    }

//    @PutMapping("/{id}")
//    public Mono<Product> updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
//        return productService.findById(id)
//                .flatMap(product -> {
//                    product.setName(updatedProduct.getName());
//                    product.setPrice(updatedProduct.getPrice());
//                    product.setQuantity(updatedProduct.getQuantity());
//                    product.setUpdateDate(Instant.now());
//                    return productRepository.save(product);
//                });
//    }

}
