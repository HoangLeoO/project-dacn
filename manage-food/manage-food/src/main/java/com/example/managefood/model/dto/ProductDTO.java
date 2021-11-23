package com.example.managefood.model.dto;


public class ProductDTO {
    private long id ;

    private String name ;

    private long price ;

    private String imageUrl;

    private String description ;

    private long quantity ;

    private long categoryProduct;


    public ProductDTO(long id, String name, long price, String imageUrl, String description, long quantity, long categoryProduct) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.quantity = quantity;
        this.categoryProduct = categoryProduct;
    }

    public ProductDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(long categoryProduct) {
        this.categoryProduct = categoryProduct;
    }
}
