package com.json.homework.models.dtos.viewDtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class UsersSoldProductsViewDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Set<ProductsNameAndPriceBuyerFirstAndLastName> soldProducts;

    public UsersSoldProductsViewDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Length(min = 3)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductsNameAndPriceBuyerFirstAndLastName> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductsNameAndPriceBuyerFirstAndLastName> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
