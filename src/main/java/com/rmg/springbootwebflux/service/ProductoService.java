package com.rmg.springbootwebflux.service;

import com.rmg.springbootwebflux.models.documents.Producto;
import com.rmg.springbootwebflux.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Mono<Producto> saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Flux<Producto> findAll() {
        return productoRepository.findAll();
    }
}
