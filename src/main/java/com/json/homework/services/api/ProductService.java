package com.json.homework.services.api;

import com.json.homework.models.dtos.seedDtos.ProductSeedDto;
import com.json.homework.models.dtos.viewDtos.ProductInRangeViewDto;
import com.json.homework.models.dtos.viewDtos.UsersSoldProductsViewDto;
import com.json.homework.models.entities.Product;

import java.util.List;

public interface ProductService {
    void seedProduct(ProductSeedDto[] productSeedDtos);
    List<ProductInRangeViewDto> getAllProductsInRange();
}
