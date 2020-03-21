package com.json.homework.models.dtos.viewDtos;

import com.google.gson.annotations.Expose;

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
