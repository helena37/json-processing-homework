package com.json.homework.models.dtos.viewDtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jdk.jfr.Name;

import javax.persistence.Transient;
import java.util.Set;

public class CategoriesByProductsCountViewDto {
    @Expose
    private String name;
    @Expose
    private ProductsCountAndPriceViewDto productsInCategory;

    public CategoriesByProductsCountViewDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductsCountAndPriceViewDto getProductsInCategory() {
        return productsInCategory;
    }

    public void setProductsInCategory(ProductsCountAndPriceViewDto productsInCategory) {
        this.productsInCategory = productsInCategory;
    }
}
