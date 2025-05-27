package com.rmg.springbootwebflux.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "productos")
public record ProductoRecord (
        @Id String id,
        String nombre,
        double precio,
        LocalDateTime createDate,
        LocalDateTime updateDate
) {}
