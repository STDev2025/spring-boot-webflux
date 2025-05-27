package com.rmg.springbootwebflux.service;

import com.rmg.springbootwebflux.models.documents.LabResult;
import com.rmg.springbootwebflux.repository.LabResultRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class LabResultService {

    private final LabResultRepository repository;

    public LabResultService(LabResultRepository repository) {
        this.repository = repository;
    }

    public Mono<LabResult> findById(String id) {
        return repository.findById(id);
    }

    public Flux<LabResult> findByFecha(LocalDate fecha) {
        return repository.findByFechaPrueba(fecha);
    }

    public Flux<LabResult> findByTipoPrueba(String tipo) {
        return repository.findByTipoPrueba(tipo);
    }

    public Flux<LabResult> findByResultadoGreaterThan(BigDecimal valor) {
        return repository.findByResultadoGreaterThan(valor);
    }

    public Flux<LabResult> findByResultadoBetween(BigDecimal min, BigDecimal max) {
        return repository.findByResultadoBetween(min, max);
    }

    public Flux<LabResult> findByRangoNormal(String rango) {
        return repository.findByRangoNormal(rango);
    }

    public Flux<LabResult> findByInterpretacion(String keyword) {
        return repository.findByInterpretacionLike(keyword);
    }

    public Mono<LabResult> save(LabResult result) {
        return repository.save(result);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

}
