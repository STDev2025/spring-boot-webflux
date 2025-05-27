package com.rmg.springbootwebflux.controller;

import com.rmg.springbootwebflux.models.documents.Producto;
import com.rmg.springbootwebflux.models.documents.ProductoRecord;
import com.rmg.springbootwebflux.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public Mono<Producto> createProducto(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    @GetMapping
    public Flux<Producto> getAllProductos() {
        return productoService.findAll();
    }
}

