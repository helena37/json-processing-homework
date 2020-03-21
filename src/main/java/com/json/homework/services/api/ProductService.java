package com.json.homework.services.api;

import com.json.homework.models.dtos.seedDtos.ProductSeedDto;
import com.json.homework.models.dtos.viewDtos.ProductInRangeViewDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProduct(ProductSeedDto[] productSeedDtos);
    //Query 1
    List<ProductInRangeViewDto> getAllProductsInRange();
}
