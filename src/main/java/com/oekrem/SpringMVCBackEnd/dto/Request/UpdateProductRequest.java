package com.oekrem.SpringMVCBackEnd.dto.Request;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {

    private String name;
    private Long categoryId;
    private String description;
    private BigDecimal price;
    private String image;

    public UpdateProductRequest() {
    }

    public UpdateProductRequest(String name, Long categoryId, String description, BigDecimal price, String image) {
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "UpdateProductRequest{" +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
