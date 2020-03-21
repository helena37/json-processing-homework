package com.json.homework.models.dtos.viewDtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductsNameAndPriceBuyerFirstAndLastName {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String buyerFirstName;
    @Expose
    private String buyerLastName;

    public ProductsNameAndPriceBuyerFirstAndLastName() {
    }

    @NotNull
    @Length(min = 3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    @NotNull
    @Length(min = 3)
    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }
}
