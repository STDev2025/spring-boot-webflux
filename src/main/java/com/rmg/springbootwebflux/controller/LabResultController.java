package com.rmg.springbootwebflux.controller;

import com.rmg.springbootwebflux.models.documents.LabResult;
import com.rmg.springbootwebflux.service.LabResultService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/labresults")
public class LabResultController {

    private final LabResultService service;

    public LabResultController(LabResultService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Mono<LabResult> getById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/date/{fechaPrueba}")
    public Flux<LabResult> findByFecha(@PathVariable String fechaPrueba) {
        return service.findByFecha(LocalDate.parse(fechaPrueba));
    }

    @GetMapping("/tipo/{tipoPrueba}")
    public Flux<LabResult> findByTipoPrueba(@PathVariable String tipoPrueba) {
        return service.findByTipoPrueba(tipoPrueba);
    }

    @GetMapping("/resultado/greater/{valor}")
    public Flux<LabResult> findByResultadoGreaterThan(@PathVariable BigDecimal valor) {
        return service.findByResultadoGreaterThan(valor);
    }

    @GetMapping("/resultado/range")
    public Flux<LabResult> findByResultadoBetween(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return service.findByResultadoBetween(min, max);
    }

    @GetMapping("/rango/{rango}")
    public Flux<LabResult> findByRangoNormal(@PathVariable String rango) {
        return service.findByRangoNormal(rango);
    }

    @GetMapping("/interpretacion")
    public Flux<LabResult> findByInterpretacion(@RequestParam String q) {
        return service.findByInterpretacion(q);
    }

    @PostMapping
    public Mono<LabResult> create(@RequestBody LabResult lab) {
        return service.save(lab);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

}
