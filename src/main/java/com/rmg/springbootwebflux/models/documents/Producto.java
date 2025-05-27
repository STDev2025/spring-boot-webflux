package com.rmg.springbootwebflux.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "producto")
public class Producto {

    @Id
    private String id;
    private String nombre;
    private BigDecimal precio;
    private Instant createDate;
    private Instant updateDate;

    public Producto(String id, String nombre, BigDecimal precio, Instant createDate, Instant updateDate) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
