package com.rmg.springbootwebflux.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Document(collection = "lab_results")
public record LabResult(
        @Id String id,
        LocalDate fechaPrueba,
        String tipoPrueba,
        String rangoNormal,
        BigDecimal resultado,
        Instant fechaCreated,
        Instant fechaUpdated,
        String interpretacion
) {}
