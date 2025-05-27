package com.rmg.springbootwebflux.config;

import com.rmg.springbootwebflux.models.documents.Producto;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ProductoAuditingListener extends AbstractMongoEventListener<Producto> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Producto> event) {
        Producto producto = event.getSource();
        Instant now = Instant.now();

        // Si el producto no tiene fecha de creación, la asignamos
        if (producto.getCreateDate() == null) {
            producto.setCreateDate(now);
        }

        // Siempre actualizamos la fecha de modificación
        producto.setUpdateDate(now);
    }
}
