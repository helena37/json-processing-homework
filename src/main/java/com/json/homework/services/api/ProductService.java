package com.json.homework.services.api;

import com.json.homework.models.dtos.ProductSeedDto;

public interface ProductService {
    void seedProduct(ProductSeedDto[] productSeedDtos);
}
