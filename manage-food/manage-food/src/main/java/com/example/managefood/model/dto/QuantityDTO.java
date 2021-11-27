package com.example.managefood.model.dto;

public class QuantityDTO {
    private long id ;

    private long quantity;

    public QuantityDTO(long id, long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public QuantityDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
