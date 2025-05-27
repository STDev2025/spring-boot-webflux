package com.rmg.springbootwebflux.repository;

import com.rmg.springbootwebflux.models.documents.LabResult;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface LabResultRepository extends ReactiveMongoRepository<LabResult, String> {

    // Buscar un resultado por ID
    Mono<LabResult> findById(String id);

    // Buscar todos por fecha exacta
    Flux<LabResult> findByFechaPrueba(LocalDate fechaPrueba);

    // Buscar por tipo de prueba exacta
    Flux<LabResult> findByTipoPrueba(String tipoPrueba);

    // Buscar resultados mayores a un valor
    @Query("{ 'resultado' : { $gt: ?0 } }")
    Flux<LabResult> findByResultadoGreaterThan(BigDecimal valor);

    // Buscar resultados entre un rango
    @Query("{ 'resultado' : { $gte: ?0, $lte: ?1 } }")
    Flux<LabResult> findByResultadoBetween(BigDecimal min, BigDecimal max);

    // Buscar por coincidencia exacta de rango normal
    Flux<LabResult> findByRangoNormal(String rangoNormal);

    // Buscar por palabra clave dentro de la interpretación
    @Query("{ 'interpretacion' : { $regex: ?0, $options: 'i' } }")
    Flux<LabResult> findByInterpretacionLike(String keyword);

}
