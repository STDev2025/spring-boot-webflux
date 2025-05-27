package com.rmg.springbootwebflux.models.documents;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "producto")
public record Producto(
        @Id String id,
        String nombre,
        BigDecimal precio,
        @CreatedDate Instant createDate,
        @LastModifiedDate Instant updateDate
) {}
